package com.example.autocodetemplate.ohter.practice.designpattern;

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