package com.biprom.bram.backend.data.entity.mongodbEntities;

public class ImportKlanten {

        @org.springframework.data.annotation.Id
        public String id;

        public String bedrijfsNaam;
        public String commentaar;
        public String btwNummer;

        public String telefoonNummer;
        public String straat;
        public String postcode;
        public String stad;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBedrijfsNaam() {
        return bedrijfsNaam;
    }

    public void setBedrijfsNaam(String bedrijfsNaam) {
        this.bedrijfsNaam = bedrijfsNaam;
    }

    public String getCommentaar() {
        return commentaar;
    }

    public void setCommentaar(String commentaar) {
        this.commentaar = commentaar;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public void setBtwNummer(String btwNummer) {
        this.btwNummer = btwNummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public ImportKlanten() {



        }

    }


