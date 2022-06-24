package com.example.autocodetemplate.finance.logic;

import com.example.autocodetemplate.finance.dto.QuotaDTO;
import com.example.autocodetemplate.util.HttpGetUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Component
public class StockLogic {

    public List<QuotaDTO> getReleaseQuato(List<String> codes) {
        if (CollectionUtils.isEmpty(codes)) {
            return null;
        }

        StringJoiner sj = new StringJoiner(",", "", "");
        for (String code : codes) {
            sj.add(code);
        }

        String response = HttpGetUtils.get("http://qt.gtimg.cn/q=" + sj.toString());

        List<QuotaDTO> quotaDTOS = new ArrayList<>();
        if (response != null) {
            String[] arrays = response.split(";");
            for (String array : arrays) {
                QuotaDTO quotaDTO = new QuotaDTO();
                if (StringUtils.isNotBlank(array)) {
                    String[] fields = array.split("~");

                    quotaDTO.setName(getF(fields, 1));
                    quotaDTO.setCode(getF(fields, 2));
                    quotaDTO.setCurPrice(getF(fields, 3));
                    quotaDTO.setYesClosingPrice(getF(fields, 4));
                    quotaDTO.setNowOpenPrice(getF(fields, 5));
                    quotaDTO.setVolume(getF(fields, 6));
                    quotaDTO.setBuyVol(getF(fields, 7));
                    quotaDTO.setSellVol(getF(fields, 8));
                    quotaDTO.setTime(getF(fields, 30));
                    quotaDTO.setUpAndDown(getF(fields, 31));
                    quotaDTO.setUpAndDownPer(getF(fields, 32));
                    quotaDTO.setHighest(getF(fields, 33));
                    quotaDTO.setLowest(getF(fields, 34));
                    quotaDTO.set价格成交量成交额(getF(fields, 35));
                    quotaDTO.set成交量手(getF(fields, 36));
                    quotaDTO.set成交额(getF(fields, 37));
                    quotaDTO.setTurnoverRate(getF(fields, 38));
                    quotaDTO.setPe(getF(fields, 39));
                    quotaDTO.set最高(getF(fields, 41));
                    quotaDTO.set最低(getF(fields, 42));
                    quotaDTO.set振幅(getF(fields, 43));
                    quotaDTO.setCirculatingMarketVal(getF(fields, 44));
                    quotaDTO.setTotalMarketVal(getF(fields, 45));
                    quotaDTO.setPb(getF(fields, 46));
                    quotaDTO.set涨停价(getF(fields, 47));
                    quotaDTO.set跌停价(getF(fields, 48));
                }
                quotaDTOS.add(quotaDTO);
            }
        }

        return quotaDTOS;
    }

    private String getF(String[] fields, int getI) {
        if (fields == null) {
            return StringUtils.EMPTY;
        }

        if (fields.length > getI) {
            return fields[getI];
        }

        return StringUtils.EMPTY;
    }

}
