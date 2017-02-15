package com.example.mooncat.clientmal;

class Manga extends Media{

    private String chapters;
    private String volumes;
    private String myReadChapters;
    private String myReadVolumes;

    public void setStatus(String status) {
        switch (status) {
            case "1":
                this.setStatus("Publishing");
                break;
            case "2":
                this.setStatus("Finished");
                break;
            case "3":
                this.setStatus("Not yet published");
                break;
            default:
                super.setStatus(status);
        }
    }

    public void setType(String type) {
        super.setType("Manga");
    }

    public void setChapters(String chapters) {
        this.chapters = chapters;
    }

    public void setVolumes(String volumes) {
        this.volumes = volumes;
    }

    public void setMyReadChapters(String myReadChapters) {
        this.myReadChapters = myReadChapters;
    }

    public void setMyReadVolumes(String myReadVolumes) {
        this.myReadVolumes = myReadVolumes;
    }

    public String getChapters() {
        return chapters;
    }

    public String getVolumes() {
        return volumes;
    }

    public String getMyReadChapters() {
        return myReadChapters;
    }

    public String getMyReadVolumes() {
        return myReadVolumes;
    }
}
