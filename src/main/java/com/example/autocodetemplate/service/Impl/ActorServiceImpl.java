package com.example.autocodetemplate.service.Impl;

import com.example.autocodetemplate.dao.ActorDao;
import com.example.autocodetemplate.domain.Actor;
import com.example.autocodetemplate.filter.ActorFilter;
import com.example.autocodetemplate.service.ActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service(value = "actorService")
public class ActorServiceImpl implements ActorService {

    private Logger logger = LoggerFactory.getLogger(ActorServiceImpl.class);

    @Resource
    private ActorDao actorDao;


    @Override
    public Actor queryByActorFilter(ActorFilter filter) {
        return actorDao.queryByActorFilter(filter);
    }

    /**
     * value (也可使用 cacheNames) : 可看做命名空间，表示存到哪个缓存
     * key : 表示命名空间下缓存唯一key,使用Spring Expression Language
     * condition : 表示在哪种情况下才缓存结果(对应的还有unless,哪种情况不缓存),同样使用SpEL
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "cacheDB1", key = "#id", condition = "#id !=  0 ")
    public Actor queryByCache(Integer id) {

        try {
            Thread.sleep(2000L);

        } catch (Exception e) {

        }
        Actor actor = new Actor();
        actor.setId(id);
        actor.setName("no_" + id);
        actor.setUserId(100 + id);
        actor.setUserType(1);
        actor.setCreateTime(new Date());

        logger.debug("从数据库拿数据,并存入指定key缓存");

        return actor;
    }

    /**
     * - value  命名空间  表示删除哪个命名空间中的缓存
     * - allEntries: 标记是否删除命名空间下所有缓存，默认为false
     * @param id
     * @return
     */
    @Override
    @CacheEvict(value = "cacheDB1", allEntries = true)
    public Actor deleteAllCache(Integer id) {
        logger.debug("删除所有key缓存");

        return null;
    }

    /**
     * - key: 同Cacheable注解，代表需要删除的命名空间下唯一的缓存key。
     * @param id
     * @return
     */
    @Override
    @CacheEvict(value = "cacheDB1", key = "#id")
    public Actor deleteCacheById(Integer id) {
        logger.debug("删除指定key缓存");
        return null;
    }

    @Override
    @CachePut(value = "cacheDB1", key = "#id")
    public Actor flushCache(Integer id) {
        Actor actor = new Actor();
        actor.setId(id);
        actor.setName("no_" + id);
        actor.setUserId(100 + id);
        actor.setUserType(1);
        actor.setCreateTime(new Date());

        logger.debug("从数据库拿数据，刷新指定key缓存");

        return actor;
    }
}
