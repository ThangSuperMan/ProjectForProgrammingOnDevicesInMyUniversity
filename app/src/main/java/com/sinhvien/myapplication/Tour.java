package com.sinhvien.myapplication;

public class Tour {
    private String id;
    private String name;
    private int image;
    private String timeline;
    private int totalPrice;

    public Tour(String id, String name, int image, String timeline, int totalPrice) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.timeline = timeline;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
