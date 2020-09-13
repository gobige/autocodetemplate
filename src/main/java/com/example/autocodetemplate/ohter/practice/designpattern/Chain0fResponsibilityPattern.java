package com.example.autocodetemplate.ohter.practice.designpattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式很多对象由每一个对象对其下家的引用而连接起来形成一条链。请求在这个链上传递，直到链上的某一个对象决定处理此请求。
 * 发出这个请求的客户端并不知道链上的哪一个对象最终处理这个请求，这使得系统可以在不影响客户端的情况下动态地重新组织和分配责任。
 * Tomcat中的Filter就是使用了责任链模式，创建一个Filter除了要在web.xml文件中做相应配置外，还需要实现javax.servlet.Filter接口。
 */
public class Chain0fResponsibilityPattern {

}



@Component
class ChainHandler1 implements BaseChainHandler {

    @Autowired
    private ChainHandler2 nextHandLer;

    @Override
    public List<Object> filter(handleObj obj) {
        List<Object> returnList = new ArrayList<>();
        for (Object o : obj.getItem()) {
            if (o.equals("test")) {
                returnList.add(o);
            }
        }

        return returnList;
    }

    @Override
    public void handle(handleObj obj) {
        List<Object> returnList = this.filter(obj);

        //todo handle returnList
        returnList.stream().forEach(o -> o.getClass());

        nextHandLer.handle(obj);
    }
}


@Component
class ChainHandler2 implements BaseChainHandler {

    @Override
    public List<Object> filter(handleObj obj) {
        List<Object> returnList = new ArrayList<>();
        for (Object o : obj.getItem()) {
            if (o.equals("test2")) {
                returnList.add(o);
            }
        }

        return returnList;
    }

    @Override
    public void handle(handleObj obj) {
        List<Object> returnList = this.filter(obj);

        //todo handle returnList
        returnList.stream().forEach(o -> o.getClass());

    }
}

interface BaseChainHandler {
    List<Object> filter(handleObj obj);

    void handle(handleObj obj);
}

class handleObj {
    List<Object> item;

    public List<Object> getItem() {
        return item;
    }

    public void setItem(List<Object> item) {
        this.item = item;
    }
}