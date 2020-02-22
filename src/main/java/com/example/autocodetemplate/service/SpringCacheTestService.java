package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.Actor;
import com.example.autocodetemplate.filter.ActorFilter;

public interface SpringCacheTestService {

    Actor queryByCache(Integer id);

    Actor deleteAllCache( );

    Actor deleteCacheById(Integer id);



    Actor flushCache(Integer id);

}