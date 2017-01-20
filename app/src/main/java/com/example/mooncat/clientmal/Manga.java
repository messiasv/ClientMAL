package com.example.mooncat.clientmal;

class Manga extends Media{
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
}
