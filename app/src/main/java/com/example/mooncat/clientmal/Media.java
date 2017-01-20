package com.example.mooncat.clientmal;

public class Media {
    private String title;
    private String status;
    private String type;
    private String image;

    public Media() {
    }

    public Media(String title, String status, String type, String image) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
