package com.example.mooncat.clientmal;

public class Manga {
    private String title;
    private String status;
    private String type;
    private String image;

    public Manga(String title, String status, String type, String image) {
        this.title = title;
        this.status = status;
        this.type = type;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }
}
