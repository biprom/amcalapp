package com.biprom.bram.backend.data.entity.mongodbEntities.GoogleMapsEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Elements {

    private  Distance[] elements;

    public Distance[] getElements() {
        return elements;
    }

    public void setElements(Distance[] elements) {
        this.elements = elements;
    }
}
