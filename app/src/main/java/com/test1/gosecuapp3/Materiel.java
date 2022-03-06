package com.test1.gosecuapp3;

public class Materiel {

    private String materiel;
    private String role;
    private String coverImage;

    public Materiel() {

    }

    public Materiel(String materiel, String role, String coverImage) {
        this.materiel = materiel;
        this.role = role;
        this.coverImage = coverImage;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

}
