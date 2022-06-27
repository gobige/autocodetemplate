package com.example.autocodetemplate.finance.logic;

import com.example.autocodetemplate.finance.dto.QuotaDTO;
import com.example.autocodetemplate.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StockLogicTest {
    StockLogic stockLogic = new StockLogic();

    @Test
    public void testGetReleaseQuato()  {
        List<QuotaDTO> result = stockLogic.getReleaseQuato(Arrays.asList("sz000858"));
        System.out.println(result);

        Assert.assertEquals("五 粮 液", result.get(0).getName());
    }

    @Test
    public void getAllPeStock() throws Exception {
        File sh = ResourceUtils.getFile("classpath:sh.txt");
        File sz = ResourceUtils.getFile("classpath:sz.txt");

        String shStocks = FileUtil.fileInputStreamToString(sh);
        String szStocks = FileUtil.fileInputStreamToString(sz);

        List<String> shcodes = getStockCodes(shStocks,"sh");
        List<String> szdes = getStockCodes(szStocks,"sz");

        shcodes.addAll(szdes);
        final int patch = 20;
        List<List<String>> partis = ListUtils.partition(shcodes, patch);

        List<QuotaDTO> result = new ArrayList<>();
        for (List<String> parti : partis) {
            List<QuotaDTO> patches = stockLogic.getReleaseQuato(parti);
            result.addAll(patches);
        }

        Double peLow = Double.valueOf("10");

        List<QuotaDTO> lowPes = result.stream().filter(t -> StringUtils.isNotBlank(t.getPe())
                && peLow.compareTo(Double.valueOf(t.getPe())) == 1 && Double.valueOf(0.0).compareTo(Double.valueOf(t.getPe())) == -1).collect(Collectors.toList());

        lowPes = lowPes.stream().filter(t -> StringUtils.isNotBlank(t.getTotalMarketVal()) && Double.valueOf(100.0).compareTo(Double.valueOf(t.getTotalMarketVal())) == -1).collect(Collectors.toList());

        log.info("计算结果：{}", lowPes.size());
        for (QuotaDTO lowPe : lowPes) {
            log.info("低PE公司--名称，股票代码，价格，市值，PE，PB---{},{},{},{},{},{}", lowPe.getName(), lowPe.getCode(), lowPe.getCurPrice(), lowPe.getTotalMarketVal(), lowPe.getPe(), lowPe.getPb());
        }

        log.info(lowPes.stream().map(QuotaDTO::getCode).collect(Collectors.joining(",")));
    }

    private List<String> getStockCodes(String szStocks,String pre) {
        int s = szStocks.indexOf("(");
        int e = szStocks.indexOf(")");

        List<String> codes = new ArrayList<>();
        while (s > 0 && e > 0) {
            String subs = szStocks.substring(s + 1, e);
            codes.add(pre +subs);
            s = szStocks.indexOf("(", s + 1);
            e = szStocks.indexOf(")", e + 1);
        }
        return codes;
    }
}

