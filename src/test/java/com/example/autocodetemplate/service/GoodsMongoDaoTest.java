package com.example.autocodetemplate.service;

import com.example.autocodetemplate.dao.mongodb.GoodsMongoDao;
import com.example.autocodetemplate.domain.mongodb.GoodsMongo;
import com.example.autocodetemplate.domain.mongodb.SupplierMongo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMongoDaoTest {

    /**
     * Spring 对 mongodb的支持，MongoTemplate,MongoRepository
     *
     */
    @Resource
    private GoodsMongoDao goodsMongoDao;

    @Test
    public void testsaveGoods()    {
        GoodsMongo goodsMongo = new GoodsMongo();
        goodsMongo.setGoodsId(1);
        goodsMongo.setSupplier(new SupplierMongo(111,"nike"));
        goodsMongo.setGoodsName("内马尔毒蜂 TF 足球鞋");
        goodsMongo.setCatId(5);
        goodsMongo.setBrandId(3);
        goodsMongo.setMoq(2);
        goodsMongo.setGoodsDesc("内马尔毒蜂 TF 足球鞋 213 重磅出击，谁与争锋");
        goodsMongo.setGoodsThumb("www.baidu.com");

        goodsMongoDao.save(goodsMongo);
        GoodsMongo goodsMongo2 = new GoodsMongo();
        goodsMongo2.setGoodsId(2);
        goodsMongo2.setSupplier(new SupplierMongo(222,"adidas"));
        goodsMongo2.setGoodsName("皮尔洛传奇 TF 足球鞋");
        goodsMongo2.setCatId(3);
        goodsMongo2.setBrandId(1);
        goodsMongo2.setMoq(54);
        goodsMongo2.setGoodsDesc("皮尔洛传奇 TF 足球鞋 213 重磅出击，谁与争锋");
        goodsMongo2.setGoodsThumb("www.baidu.com");

        goodsMongoDao.save(goodsMongo2);
    }

    @Test
    public void testupdateGoods()    {
        GoodsMongo goodsMongo2 = new GoodsMongo();
        goodsMongo2.setGoodsId(1);
        goodsMongo2.setGoodsName("博格巴 TF 足球鞋");
        goodsMongo2.setCatId(9);
        goodsMongo2.setBrandId(6);
        goodsMongo2.setMoq(8);
        goodsMongo2.setGoodsDesc("博格巴舞王  AG 足球鞋 213 重磅出击，谁与争锋");
        goodsMongo2.setGoodsThumb("www.baidu.com");

        goodsMongoDao.save(goodsMongo2);
    }

    @Test
    public void testremoveGoods()    {

        goodsMongoDao.deleteByGoodsId(2);
    }


    @Test
    public void testfindGoodsById()    {
        GoodsMongo goodsMongo2 = goodsMongoDao.findByGoodsId(2);
        System.out.println(goodsMongo2.toString());
    }
}
