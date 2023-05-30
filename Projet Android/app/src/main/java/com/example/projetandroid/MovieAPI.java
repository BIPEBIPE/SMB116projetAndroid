package com.example.projetandroid;

public class MovieAPI {
    String Id;
    String titre;
    String ReleaseDate;
    String Desciption;
    String Logo;
    String Note;

    public MovieAPI() {

    }

    public MovieAPI(String id, String titre, String releaseDate, String desciption, String logo, String note) {
        Id = id;
        this.titre = titre;
        ReleaseDate = releaseDate;
        Desciption = desciption;
        Logo = logo;
        Note = note;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getDesciption() {
        return Desciption;
    }

    public void setDesciption(String desciption) {
        Desciption = desciption;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @Override
    public String toString() {
        return "MovieAPI{" +
                "Id='" + Id + '\'' +
                ", titre='" + titre + '\'' +
                ", ReleaseDate='" + ReleaseDate + '\'' +
                ", Desciption='" + Desciption + '\'' +
                ", Logo='" + Logo + '\'' +
                ", Note='" + Note + '\'' +
                '}';
    }
}

