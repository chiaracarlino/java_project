package com.epf.persistence.model;

public class Maps {
    private Long idMap;
    private int ligne;
    private int colonne;
    private String cheminImage;

    public Maps() {
    }

    public Maps(Long idMap, int ligne, int colonne, String cheminImage) {
        this.idMap = idMap;
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = cheminImage;
    }

    public Long getIdMap() {
        return idMap;
    }

    public void setIdMap(Long idMap) {
        this.idMap = idMap;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
}

