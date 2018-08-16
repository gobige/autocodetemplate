package com.example.autocodetemplate.ohter.practice.designpattern;

import java.util.Collection;

/**
 * 组合模式
 * 组合模式，就是在一个对象中包含其他对象，这些被包含的对象可能是终点对象（不再包含别的对象），也有可能是非终点对象（其内部还包含其他对象，或叫组对象），
 * 我们将对象称为节点，即一个根节点包含许多子节点，这些子节点有的不再包含子节点，而有的仍然包含子节点，以此类推
 */
public class Combination {

}

class subject {
    private String content;
    private String author;
    private String time;

    private Collection<comment> comments;
}

class comment {
    private String content;
    private String replyName;
    private String time;

    private Collection<reply> replies;
}

class reply {
    private String content;
    private String replyName;
    private String time;
}