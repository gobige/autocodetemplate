package com.example.autocodetemplate.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user_body_info")
public class UserBodyInfoMixDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = false)
    private String name;

    @Column(nullable = true, unique = false)
    private Integer age;

    @Column(nullable = true, unique = false)
    private Date birthday;
    @Column(nullable = true, unique = false)
    private String rec;
    @Column(nullable = true, unique = false)
     private String test;
    @Column(nullable = true, unique = false)
    private Integer sex;
    @Column(nullable = true, unique = false)
     private Double weight;
    @Column(nullable = true, unique = false)
     private Double height;



}
