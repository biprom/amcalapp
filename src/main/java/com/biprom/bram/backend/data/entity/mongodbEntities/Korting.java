package com.biprom.bram.backend.data.entity.mongodbEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

public class Korting {
    @Id
    @JsonIgnore
    String index;

    private String korting;
    private String effKorting;

    public Korting(String korting, String effKorting) {
        this.korting = korting;
        this.effKorting = effKorting;
    }

    public String getKorting() {
        return korting;
    }

    public void setKorting(String korting) {
        this.korting = korting;
    }

    public String getEffKorting() {
        return effKorting;
    }

    public void setEffKorting(String effKorting) {
        this.effKorting = effKorting;
    }
}
