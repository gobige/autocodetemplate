package com.example.autocodetemplate.controller;

import com.example.autocodetemplate.domain.Actor;
import com.example.autocodetemplate.filter.SpringTestFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * <p>爱车小屋</p>
 * <p>Project: carhouse-xx</p>
 * <p>ModuleID: xx</p>
 * <p>Comments: xx</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
@Controller
@Slf4j
@RequestMapping("spring-test")
public class SpringPracticeController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient webClient;


    @RequestMapping(value = "request", method = {RequestMethod.POST},// produces 指定返回值类型  consumes 指定处理请求的提交内容类型
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = "ddd=sss", params = "name=yates")
// params： 指定request中必须包含某些参数值是，才让该方法处理。  headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求。
    @ResponseBody // 在HandlerAdapter.handle()中完成Response的输出
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> testRequestMapping(@RequestBody() SpringTestFilter tempFilter) {
        Map<String, Object> result = new HashMap<>();
        result.put("name", "yates");

        return result;
    }


    @PostMapping(value = "valid")
    @ResponseBody
    public Map<String, Object> testRequestValid(@RequestBody() @Valid SpringTestFilter tempFilter, BindingResult validResult) {
        if (validResult.hasErrors()) {
            log.warn("Binding Errors: {}", validResult);
            throw new InvalidParameterException(validResult.toString());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("name", "yates");

        return result;
    }

    @PostMapping(value = "exception")
    @ResponseBody
    public Map<String, Object> testRequestException(@RequestBody() @Valid SpringTestFilter tempFilter, BindingResult validResult) {
        if (validResult.hasErrors()) {
            log.warn("Binding Errors: {}", validResult);
            throw new ValidationException(validResult.toString());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("name", "yates");

        return result;
    }

    @GetMapping("resttemplate")
    @ResponseBody // 自己配置restTemplate实例
    public void testRestTemplate() {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8090//cache/actor/{id}")
                .build(1);

        ResponseEntity<Actor> c = restTemplate.getForEntity(uri, Actor.class);
        log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
        log.info("Actor: {}", c.getBody());

        String validUrl = "http://localhost:8090/spring-test/valid";
        SpringTestFilter tempFilter = new SpringTestFilter();
        tempFilter.setNum(123);
        tempFilter.setTestNotNullParam("yautes");
        Object response = restTemplate.postForObject(validUrl, tempFilter, Object.class);
        log.info("New tempFilter: {}", response);
    }

    /**
     * reactive方式处理HTTP请求的非阻塞的客户端
     */
    @GetMapping("webClient")
    @ResponseBody // 自己配置restTemplate实例
    public void testWebclient() throws Exception {
        CountDownLatch cdl = new CountDownLatch(2);

        webClient.get()
                .uri("/cache/actor/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(Actor.class)
                .doOnError(t -> log.error("Error: ", t))
                .doFinally(s -> cdl.countDown())
                .subscribeOn(Schedulers.single())
                .subscribe(c -> log.info("Coffee 1: {}", c));

        Mono<SpringTestFilter> americano = Mono.just(
                new SpringTestFilter("yates", 12)
        );
        webClient.post()
                .uri("/coffee/")
                .body(americano, SpringTestFilter.class)
                .retrieve()
                .bodyToMono(SpringTestFilter.class)
                .doFinally(s -> cdl.countDown())
                .subscribeOn(Schedulers.single())
                .subscribe(c -> log.info("Coffee Created: {}", c));

        cdl.await();
    }

    // HATEOAS  Hybermedia As The Engine Of Application State,Rest统一接口必要组成部分
    // 表述中的超链接会提供服务所需各种REST接口信息 2无需事先约定如何访问服务
    // HAL Hypertext Application Language;是一种简单的格式，为API中资源提供简单一致的链接
    // HAL 1. 链接 2. 内嵌资源 3. 状态
    // 引包spring-boot-starter-data-rest


    /**
     * 常见会话解决方案 粘性会话 Sticky Session:一个用户会话统一由集群中一个节点负责,会话复制 Session Replicaqtion：集群中每个节点都有所有用户会话
     * ,集中会话 Centralized Session，使用redis，jdbc实现会话的管理
     *
     * @param name
     * @return
     */
    @GetMapping("spring-session")
    @ResponseBody
    public Map<String, Object> springSessionTest(HttpSession httpSession, @RequestParam("name") String name) {
        String hostName = (String) httpSession.getAttribute("name");
        if (StringUtils.isBlank(hostName)) {
            httpSession.setAttribute("name", name);
        }

        Map map = new HashMap();
        map.put("name", hostName);
        return map;
    }

}