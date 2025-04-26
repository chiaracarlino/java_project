package com.epf.persistence.model;

import java.util.List;

public class Maps {
    private int idMap;
    private int ligne;
    private int colonne;
    private String cheminImage;
    private List<Zombies> zombies;

    public Maps() {
    }

    public Maps(int idMap, int ligne, int colonne, String cheminImage) {
        this.idMap = idMap;
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = cheminImage;
    }

    public int getIdMap() {
        return idMap;
    }

    public void setIdMap(int id) {
        this.idMap = id;
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

    public List<Zombies> getZombies() { 
        return zombies;
    }

    public void setZombies(List<Zombies> zombies) { 
        this.zombies = zombies;
    }
}

