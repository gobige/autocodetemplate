package com.example.autocodetemplate.ohter.practice.java8;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class BigDecimalTest {
    public static void main(String[] args) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(3);

        BigDecimal loanAmount = new BigDecimal("15000.48");
        BigDecimal interestRate = new BigDecimal("0.008");

        System.out.println("贷款金额:\t" + currency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
    }
}
