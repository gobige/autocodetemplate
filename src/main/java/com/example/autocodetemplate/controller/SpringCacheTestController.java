package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.domain.Actor;
import com.example.autocodetemplate.exception.ServiceRuntimeException;
import com.example.autocodetemplate.filter.ActorFilter;
import com.example.autocodetemplate.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/cache/")
public class SpringCacheTestController {

    @Autowired
    private ActorService actorService;


    @RequestMapping(value = "/actor/get.json", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> get(@RequestBody() ActorFilter filter) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (filter == null || filter.getId() == 0) {
            throw new ServiceRuntimeException("参数不能为空");
        }

        Actor actor =actorService.queryByCache(filter.getId());

        result.put("code", 0);
        result.put("bcode",0);

        result.put("actor", actor);

        return result;
    }


    @RequestMapping(value = "/actor/all/delete.json", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> delete(@RequestBody() ActorFilter filter) throws Exception {
        Map<String, Object> result = new HashMap<>();


        Actor actor =actorService.deleteAllCache(filter.getId());

        result.put("code", 0);
        result.put("bcode",0);

        result.put("actor", actor);

        return result;
    }


    @RequestMapping(value = "/actor/delete.json", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> out(@RequestBody() ActorFilter filter) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (filter == null || filter.getId() == 0) {
            throw new ServiceRuntimeException("参数不能为空");
        }

        Actor actor =actorService.deleteCacheById(filter.getId());

        result.put("code", 0);
        result.put("bcode",0);

        result.put("actor", actor);

        return result;
    }

    @RequestMapping(value = "/actor/flush.json", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> flush(@RequestBody() ActorFilter filter) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (filter == null || filter.getId() == 0) {
            throw new ServiceRuntimeException("参数不能为空");
        }

        Actor actor =actorService.flushCache(filter.getId());

        result.put("code", 0);
        result.put("bcode",0);

        result.put("actor", actor);

        return result;
    }


}
