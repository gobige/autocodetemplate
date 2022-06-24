package com.example.autocodetemplate.finance.logic;

import com.example.autocodetemplate.finance.dto.QuotaDTO;
import com.example.autocodetemplate.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StockLogicTest {
    StockLogic stockLogic = new StockLogic();

    @Test
    public void testGetReleaseQuato() throws Exception {
        List<QuotaDTO> result = stockLogic.getReleaseQuato(Arrays.<String>asList("sz000858"));
        System.out.println(result);

        Assert.assertEquals("五 粮 液", result.get(0).getName());
    }

    @Test
    public void getAllPeStock() throws Exception {
        File file = ResourceUtils.getFile("classpath:sz.txt");

        String szStocks = FileUtil.fileInputStreamToString(file);

        int s = szStocks.indexOf("(");
        int e = szStocks.indexOf(")");

        List<String> codes = new ArrayList<>();
        while (s > 0 && e > 0) {
            String subs = szStocks.substring(s + 1, e);
            codes.add("sh"+subs);
            s = szStocks.indexOf("(", s + 1);
            e = szStocks.indexOf(")", e + 1);
        }

        List<QuotaDTO> result = stockLogic.getReleaseQuato(codes);

        Double peLow = Double.valueOf(20);

        List<QuotaDTO> lowPes = result.stream().filter(t -> peLow.compareTo(Double.valueOf(t.getPe())) == 1).collect(Collectors.toList());

        System.out.println(lowPes);
    }
}

