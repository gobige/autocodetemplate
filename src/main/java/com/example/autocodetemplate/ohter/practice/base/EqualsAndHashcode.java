package com.example.autocodetemplate.ohter.practice.base;

public class EqualsAndHashcode {
    public static void main(String[] args) {
        getNotEqualAndHashcodeEqual();
    }

    private static void getNotEqualAndHashcodeEqual() {
        String Aa = "Aa";
        String BB = "BB";

        if (Aa.equals(BB)) {
            System.out.println("Aa equals BB");
        }else {
            System.out.println("Aa not equals BB");
        }

        if (Aa.hashCode() == BB.hashCode()) {
            System.out.println("Aa hashCode equal BB");
        }else {
            System.out.println("Aa hashCode not equal BB");
        }
    }
}
