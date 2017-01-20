package com.example.mooncat.clientmal;

public class Anime {
    private String title;
    private String status;
    private String type;
    private String image;

    public Anime() {
    }

    public Anime(String title, String status, String type, String image) {
        this.title = title;
        this.status = status;
        this.type = type;
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        switch (status) {
            case "1":
                this.status = "watching";
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
            case "0":
                this.type = "Unknown";
                break;
            case "1":
                this.type = "TV";
                break;
            case "2":
                this.type = "OVA";
                break;
            case "3":
                this.type = "Movie";
                break;
            case "4":
                this.type = "Special";
                break;
            case "5":
                this.type = "ONA";
                break;
            case "6":
                this.type = "Music";
                break;
            default:
                this.type = type;
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
