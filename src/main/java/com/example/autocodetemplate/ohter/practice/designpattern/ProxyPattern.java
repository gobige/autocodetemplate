package com.example.autocodetemplate.ohter.practice.designpattern;

/**
 * 代理模式
 *
 * 原理：在不改变原始类情况下，通过引入代理类添加【附加功能】
 *
 * 方式：接口代理和类继承代理，
 *
 * 动态代理：静态代理需要对每个类创建一个代理类，增加了维护和开发成本；动态代理通过在运行时动态创建原始类对应代理类，进行替换
 *
 * AOP
 */
public class ProxyPattern {
    public static void main(String[] args) {
        ProxyVideo video = new ProxyVideo("Rick And Morty");
        video.display();
    }
}

class ProxyVideo implements video {
    private YoutuBe youtuBe;
    private String videoName;

    ProxyVideo(String videoName) {
        this.videoName = videoName;
    }


    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Override
    public void display() {
        if (youtuBe == null) {
            youtuBe = new YoutuBe(videoName);
        }
        youtuBe.display();
    }
}

class YoutuBe implements video {
    private String videoName;

    YoutuBe(String videoName) {
        this.videoName = videoName;
    }

    @Override
    public void display() {
        System.out.println("youtube playing " + videoName);
    }
}

interface video {
    void display();
}