package com.example.mooncat.clientmal;

class Anime extends Media {
    public void setStatus(String status) {
        switch (status) {
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

    public void setType(String type) {
        switch (type) {
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
                super.setType(type);
        }
    }
}
