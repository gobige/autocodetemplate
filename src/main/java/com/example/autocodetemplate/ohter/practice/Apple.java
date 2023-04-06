package com.example.autocodetemplate.ohter.practice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

 
@Data
public class Apple implements Serializable {

    private static final long serialVersionUID = 7208877163749273274L;

    public static void main(String[] args) {
        Collection<Integer> supplierIds = new LinkedList<>();
        supplierIds.add(1);
        Optional nextSupplierId =  supplierIds.stream().filter(id -> id != 1).findFirst();
        if (nextSupplierId.isPresent()) {
            System.out.println(nextSupplierId.get());
        }
    }
    public Apple() {
        super();
    }

    public Apple(Integer seqNo, Integer wight, String country) {
        this.seqNo = seqNo;
        this.wight = wight;
        this.country = country;
    }

    public Apple(Integer seqNo, Integer wight, String country,String name) {
        this.seqNo = seqNo;
        this.wight = wight;
        this.country = country;
        this.name = name;
    }

    private Integer seqNo;
    private Integer wight;
    private String country;
    private String color;
    private String name;
    private String desc;

}
