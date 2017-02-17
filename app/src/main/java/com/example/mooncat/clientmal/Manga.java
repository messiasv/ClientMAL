package com.example.mooncat.clientmal;

class Manga extends Media{

    private String chapters;
    private String volumes;
    private String myReadChapters;
    private String myReadVolumes;

    public Manga() {
        super();
        this.chapters = "";
        this.volumes = "";
        this.myReadChapters = "";
        this.myReadVolumes = "";
    }

    @Override
    public void setStatus(String status) {
        switch (status != null ? status : "") {
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

    @Override
    public void setType(String type) {
        super.setType("Manga");
    }

    public void setChapters(String chapters) {
        this.chapters = chapters != null ? chapters : "";
    }

    public void setVolumes(String volumes) {
        this.volumes = volumes != null ? volumes : "";
    }

    public void setMyReadChapters(String myReadChapters) {
        this.myReadChapters = myReadChapters != null ? myReadChapters : "";
    }

    public void setMyReadVolumes(String myReadVolumes) {
        this.myReadVolumes = myReadVolumes != null ? myReadVolumes : "";
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
