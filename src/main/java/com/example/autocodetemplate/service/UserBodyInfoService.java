package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.UserBodyInfo;

import java.util.Collection;

public interface UserBodyInfoService {

    Collection<UserBodyInfo> findAll();

    Collection<UserBodyInfo> getOne();

    void save();
    void delete();
}
