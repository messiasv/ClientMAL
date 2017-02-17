package com.example.mooncat.clientmal;

class Anime extends Media {

    private String episodes;
    private String myWatchedEpisodes;

    public Anime() {
        super();
        this.episodes = "";
        this.myWatchedEpisodes = "";
    }

    @Override
    public void setStatus(String status) {
        switch (status != null ? status : "") {
            case "1":
                this.setStatus("Airing");
                break;
            case "2":
                this.setStatus("Finished");
                break;
            case "3":
                this.setStatus("Not yet aired");
                break;
            default:
                super.setStatus(status);
        }
    }

    @Override
    public void setType(String type) {
        switch (type != null ? type : "") {
            case "0":
                this.setType("Unknown");
                break;
            case "1":
                this.setType("TV");
                break;
            case "2":
                this.setType("OVA");
                break;
            case "3":
                this.setType("Movie");
                break;
            case "4":
                this.setType("Special");
                break;
            case "5":
                this.setType("ONA");
                break;
            case "6":
                this.setType("Music");
                break;
            default:
                super.setType("");
        }
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes != null ? episodes : "";
    }

    public void setMyWatchedEpisodes(String myWatchedEpisodes) {
        this.myWatchedEpisodes = myWatchedEpisodes != null ? myWatchedEpisodes : "";
    }

    public String getEpisodes() {
        return episodes;
    }

    public String getMyWatchedEpisodes() {
        return myWatchedEpisodes;
    }
}
