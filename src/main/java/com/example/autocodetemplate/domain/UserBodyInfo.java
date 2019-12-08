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
public class UserBodyInfo implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = true, unique = false)
    private String nikeName;

    @Column(nullable = true, unique = false)
    private Date birthday;

    @Column(nullable = true, unique = false)
    private Integer num;



}
