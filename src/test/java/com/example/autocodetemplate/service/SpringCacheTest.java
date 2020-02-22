package com.example.autocodetemplate.service;

import com.example.autocodetemplate.domain.Actor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCacheTest {

    @Autowired
    private SpringCacheTestService springCacheTestService;

    @Test
    public void testQueryByCache() {
        Actor actor = springCacheTestService.queryByCache(1);
        Assert.assertNotNull(actor);
    }
    @Test
    public void testFlushCache() {
        springCacheTestService.flushCache(1);
    }
    @Test
    public void testDeleteAllCache() {
         springCacheTestService.deleteAllCache( );
    }
    @Test
    public void testDeleteCacheById() {
         springCacheTestService.deleteCacheById(1);
    }

}
