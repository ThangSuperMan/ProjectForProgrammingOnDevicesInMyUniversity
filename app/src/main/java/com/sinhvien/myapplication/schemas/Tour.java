package com.sinhvien.myapplication.schemas;

public class Tour {
    private String id;
    private String title;
    private String body;
    private String image;
    private String timeline;
    private int price;

    public Tour(String id, String name, String image, String timeline, int price) {
        this.id = id;
        this.title = name;
        this.image = image;
        this.timeline = timeline;
        this.price = price;
    }

    public Tour() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
