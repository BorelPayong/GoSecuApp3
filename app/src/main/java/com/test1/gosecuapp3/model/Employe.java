package com.test1.gosecuapp3.model;

import java.util.ArrayList;
import java.util.List;

public class Employe {
    private String nom;
    private String prenom;
    private String imgUrl;
    private String role;
    private String mdp;
    private List<Materiel> materielsEmpruntes;


    public Employe(String nom, String prenom, String imgUrl, String role, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.imgUrl = imgUrl;
        this.role = role;
        this.mdp = mdp;
        this.materielsEmpruntes = new ArrayList<>();
    }

    public void addMateriel(Materiel materiel){
        materielsEmpruntes.add(materiel);
    }


    public String getUserName(){
        return prenom.toLowerCase().charAt(0) + nom.toLowerCase();
    }

    @Override
    public String toString() {
        return "Employe{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", role='" + role + '\'' +
                ", mdp='" + mdp + '\'' +
                ", materielsEmprintes=" + materielsEmpruntes +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getRole() {
        return role;
    }

    public String getMdp() {
        return mdp;
    }

    public List<Materiel> getMaterielsEmpruntes() {
        return materielsEmpruntes;
    }

    public static Employe logIn(List<Employe> source, String userName, String mdp){
        for (Employe e : source){
            if(e.getUserName().equals(userName) && e.getMdp().equals(mdp)) return e;
        }
        return null;
    }
}