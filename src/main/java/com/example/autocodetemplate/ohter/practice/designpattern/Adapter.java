package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 适配器模式
 * 让不兼容接口转换为可兼容接口，常走位一种事后补救措施
 *
 *
 */

/**
 * 类适配模式
 * 如果 targetClass接口很多，而且 targetClass 和 CAdaptee 接口定义大部分相同
 *
 */
public class Adapter extends targetClass implements CAdaptee {

    public String readXmlByStream() {
        return readXml();
    }
}


/**
 * 适配器模式：对象适配模式
 *
 * 如果 targetClass接口很多，而且 targetClass 和 CAdaptee 接口定义大部分 不相同
 */
class Adapter2 implements CAdaptee {
    // 该对象的方法适配我所需求
    targetClass tc = new targetClass();


    public String readXmlByStream() {
        return tc.readXml();
    }
}

interface CAdaptee {
    String readXmlByStream();
}

class targetClass {
    public String readXml() {
        return "we can read readXml";
    };
}

/**
 * 让这个工具也能读取xml格式数据
 */
class ReadDataTool extends Adapter2{
    public String readJson() {
        return "i can read json data!";
    }

    @Override
    public String readXmlByStream() {
        return super.readXmlByStream();
    }
}