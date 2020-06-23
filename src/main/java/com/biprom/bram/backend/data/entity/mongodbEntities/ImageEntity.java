package com.biprom.bram.backend.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ImageEntity {

    @org.springframework.data.annotation.Id
    @JsonIgnore
    private String id;
    private String path;
    private String name;
    private String comment;
    private boolean bVoorOpPdf;

    public ImageEntity(String path, String name, String comment, boolean bVoorOpPdf) {
        this.path = path;
        this.name = name;
        this.comment = comment;
        this.bVoorOpPdf = bVoorOpPdf;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isbVoorOpPdf() {
        return bVoorOpPdf;
    }

    public void setbVoorOpPdf(boolean bVoorOpPdf) {
        this.bVoorOpPdf = bVoorOpPdf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
