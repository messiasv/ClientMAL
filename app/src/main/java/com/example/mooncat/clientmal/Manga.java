package com.example.mooncat.clientmal;

public class Manga {
    private String title;
    private String status;
    private String type;
    private String image;

    public Manga() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        switch (status) {
            case "1":
                this.status = "reading";
                break;
            case "2":
                this.status = "completed";
                break;
            case "3":
                this.status = "onhold";
                break;
            case "4":
                this.status = "dropped";
                break;
            case "6":
                this.status = "plantowatch";
                break;
            default:
                this.status = status;
        }
    }

    public void setType(String type) {
        switch (type) {
            default:
                this.type = "Manga";
        }
    }

    public void setImage(String image) {
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
