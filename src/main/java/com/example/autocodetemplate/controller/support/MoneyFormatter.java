package com.example.autocodetemplate.controller.support;

import com.example.autocodetemplate.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.format.Formatter;
import org.joda.money.Money;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class MoneyFormatter implements Formatter<Money> {
    @Override
    public Money parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isNotBlank(text)) {

        }
        return null;
    }

    @Override
    public String print(Money money, Locale locale) {
        return null;
    }
}
