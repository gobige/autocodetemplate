package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.Actor;
import com.example.autocodetemplate.filter.ActorFilter;

public interface ActorService {
    Actor queryByActorFilter(ActorFilter filter);


    Actor queryByCache(Integer id);

    Actor deleteAllCache(Integer id);

    Actor deleteCacheById(Integer id);



    Actor flushCache(Integer id);

}