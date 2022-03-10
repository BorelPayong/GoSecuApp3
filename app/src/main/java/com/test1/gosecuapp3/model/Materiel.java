package com.test1.gosecuapp3.model;

public class Materiel {
    private String code;
    private String label;

    public Materiel(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}
