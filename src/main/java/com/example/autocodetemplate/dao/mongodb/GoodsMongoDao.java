package com.example.autocodetemplate.dao.mongodb;

import com.example.autocodetemplate.domain.mongodb.GoodsMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 */

public interface GoodsMongoDao extends MongoRepository<GoodsMongo, String> {

    GoodsMongo findByGoodsId(Integer id);
    int deleteByGoodsId(Integer id);
}
