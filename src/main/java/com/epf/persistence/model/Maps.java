package com.epf.persistence.model;

import java.util.List;

public class Maps {
    private Integer idMap;
    private Integer ligne;
    private Integer colonne;
    private String cheminImage;
    private List<Zombies> zombies;

    public Maps() {}

    public Maps(Integer idMap, Integer ligne, Integer colonne, String cheminImage) {
        this.idMap = idMap;
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = cheminImage;
    }

    public Integer getIdMap() { return idMap; }
    public void setIdMap(Integer id) { this.idMap = id; }

    public Integer getLigne() { return ligne; }
    public void setLigne(Integer ligne) { this.ligne = ligne; }

    public Integer getColonne() { return colonne; }
    public void setColonne(Integer colonne) { this.colonne = colonne; }

    public String getCheminImage() { return cheminImage; }
    public void setCheminImage(String cheminImage) { this.cheminImage = cheminImage; }

    public List<Zombies> getZombies() { return zombies; }
    public void setZombies(List<Zombies> zombies) { this.zombies = zombies; }
}

