package com.example.autocodetemplate.ohter.practice.designpattern;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 设计模式联系1
 *
 */
public class DesignPractice {

    /**
     * 性能计数--收集器
     */
    class MetricsCollector {
        private MetricsStorage metricsStorage;

        public MetricsCollector(MetricsStorage metricsStorage) {
            this.metricsStorage = metricsStorage;
        }

        public void record(RequestInfo requestInfo) {
            metricsStorage.store(requestInfo);
        }

    }


}

@Getter
@Setter
class RequestInfo {
    /**
     * api-name
     */
    private String apiName;
    /**
     * response--time
     */
    private double responseTime;
    /**
     * req-time
     */
    private Long timestamp;
}

/**
 * 性能计数--存储器
 */
interface MetricsStorage {
    void store(RequestInfo requestInfo);

    Collection<RequestInfo> getRequestInfos(String apiName, long startTimeMills, long endTimeMills);

    Map<String, Collection<RequestInfo>> getRequestInfos(long startTimeMills, long endTimeMills);
}

class MemoryMetricsStorage implements MetricsStorage {

    private static final Map<String, Collection<RequestInfo>> reqs = new HashMap();

    public void store(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isEmpty(requestInfo.getApiName())) {
            return;
        }

        if (reqs.containsKey(requestInfo.getApiName())) {
            reqs.get(requestInfo.getApiName()).add(requestInfo);
        } else {
            reqs.put(requestInfo.getApiName(), Arrays.asList(requestInfo));
        }
    }

    @Override
    public Collection<RequestInfo> getRequestInfos(String apiName, long startTimeMills, long endTimeMills) {
        if (!reqs.containsKey(apiName) || CollectionUtils.isEmpty(reqs.get(apiName))) {
            return Collections.EMPTY_LIST;
        }

        Collection<RequestInfo> requestInfos = reqs.get(apiName);

        Collection<RequestInfo> results = requestInfos.stream().filter(info -> info.getTimestamp() > startTimeMills && info.getTimestamp() < endTimeMills).collect(Collectors.toList());

        return results;
    }

    @Override
    public Map<String, Collection<RequestInfo>> getRequestInfos(long startTimeMills, long endTimeMills) {

        if (reqs.isEmpty()) {
            return Collections.EMPTY_MAP;
        }

        Set<Map.Entry<String, Collection<RequestInfo>>> entrySets = reqs.entrySet();


        Map<String, Collection<RequestInfo>> results = new HashMap<>();
        for (Map.Entry<String, Collection<RequestInfo>> entrySet : entrySets) {
            Collection<RequestInfo> requestInfos = entrySet.getValue();

            requestInfos = requestInfos.stream().filter(info -> info.getTimestamp() > startTimeMills
                    && info.getTimestamp() < endTimeMills).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(requestInfos)) {
                continue;
            }

            results.put(entrySet.getKey(), requestInfos);
        }

        return results;
    }
}

@Getter
@Setter
class RequestStat {

    private Double maxResponseTime;
    private Double minResponseTime;
    private Double avgResponseTime;
    private Double p999ResponseTime;
    private Double p99ResponseTime;
    private Long count;
    private Long tps;

}

class Aggregator {
    public static RequestStat aggregate(Collection<RequestInfo> requestInfos, long durationMills) {

        if (CollectionUtils.isEmpty(requestInfos)) {
            return null;
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(maxResponseTime(requestInfos));
        requestStat.setMinResponseTime(minResponseTime(requestInfos));
        requestStat.setAvgResponseTime(avgResponseTime(requestInfos));
//        requestStat.setP999ResponseTime();
//        requestStat.setP99ResponseTime();
        requestStat.setCount(count(requestInfos));
        requestStat.setTps(tps(requestInfos, durationMills));

        return requestStat;
    }

    public static Double maxResponseTime(Collection<RequestInfo> requestInfos) {
        if (CollectionUtils.isEmpty(requestInfos)) {
            return NumberUtils.DOUBLE_ZERO;
        }

        return requestInfos.stream().max((Comparator.comparing(RequestInfo::getResponseTime))).get().getResponseTime();
    }

    public static Long count(Collection<RequestInfo> requestInfos) {
        if (CollectionUtils.isEmpty(requestInfos)) {
            return NumberUtils.LONG_ZERO;
        }

        return Long.valueOf(requestInfos.size());
    }

    public static Long tps(Collection<RequestInfo> requestInfos, long durationMills) {
        if (CollectionUtils.isEmpty(requestInfos)) {
            return NumberUtils.LONG_ZERO;
        }

        return requestInfos.size() / durationMills / 1000L;
    }

    public static Double minResponseTime(Collection<RequestInfo> requestInfos) {
        if (CollectionUtils.isEmpty(requestInfos)) {
            return NumberUtils.DOUBLE_ZERO;
        }

        return requestInfos.stream().min((Comparator.comparing(RequestInfo::getResponseTime))).get().getResponseTime();
    }

    public static Double avgResponseTime(Collection<RequestInfo> requestInfos) {
        if (CollectionUtils.isEmpty(requestInfos)) {
            return NumberUtils.DOUBLE_ZERO;
        }

        return requestInfos.stream().mapToDouble(RequestInfo::getResponseTime).average().getAsDouble();
    }
}