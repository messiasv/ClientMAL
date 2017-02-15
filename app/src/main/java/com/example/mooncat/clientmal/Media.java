package com.example.mooncat.clientmal;

import java.io.Serializable;

public class Media implements Serializable {
    private String title;
    private String english;
    private String status;
    private String type;
    private String image;
    private String synonyms;
    private String start;
    private String end;
    private String id;
    private String score;
    private String synopsis;
    private String myStartDate;
    private String myFinishDate;
    private String myScore;
    private String myStatus;

    public Media() {
    }

    public Media(String title, String status, String type, String image) {
        this.title = title;
        this.status = status;
        this.type = type;
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEnglish(String english) {
        this.english = english;
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

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setMyStartDate(String myStartDate) {
        this.myStartDate = myStartDate;
    }

    public void setMyFinishDate(String myFinishDate) {
        this.myFinishDate = myFinishDate;
    }

    public void setMyScore(String myScore) {
        this.myScore = myScore;
    }

    public void setMyStatus(String myStatus) {
        this.myStatus = myStatus;
    }

    public String getTitle() {
        return title;
    }

    public String getEnglish() {
        return english;
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

    public String getSynonyms() {
        return synonyms;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getMyStartDate() {
        return myStartDate;
    }

    public String getMyFinishDate() {
        return myFinishDate;
    }

    public String getMyScore() {
        return myScore;
    }

    public String getMyStatus() {
        return myStatus;
    }
}
