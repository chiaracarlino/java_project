package com.epf.persistence.model;

public class Zombies {
    private Long id;
    private String nom;
    private int pointDeVie;
    private int degatAttaque;
    private Long idMap;
    private int attaqueParSeconde;
    private int vitesseDeDeplacement;
    private String cheminImage;

    public Zombies() {
    }

    public Zombies(Long id, String nom, int pointDeVie, int degatAttaque, Long idMap,
                   int attaqueParSeconde, int vitesseDeDeplacement, String cheminImage) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.degatAttaque = degatAttaque;
        this.idMap = idMap;
        this.attaqueParSeconde = attaqueParSeconde;
        this.vitesseDeDeplacement = vitesseDeDeplacement;
        this.cheminImage = cheminImage;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getPointDeVie() { return pointDeVie; }
    public void setPointDeVie(int pointDeVie) { this.pointDeVie = pointDeVie; }

    public int getDegatAttaque() { return degatAttaque; }
    public void setDegatAttaque(int degatAttaque) { this.degatAttaque = degatAttaque; }

    public Long getIdMap() { return idMap; }
    public void setIdMap(Long idMap) { this.idMap = idMap; }

    public int getAttaqueParSeconde() { return attaqueParSeconde; }
    public void setAttaqueParSeconde(int attaqueParSeconde) { this.attaqueParSeconde = attaqueParSeconde; }

    public int getVitesseDeDeplacement() { return vitesseDeDeplacement; }
    public void setVitesseDeDeplacement(int vitesseDeDeplacement) { this.vitesseDeDeplacement = vitesseDeDeplacement; }

    public String getCheminImage() { return cheminImage; }
    public void setCheminImage(String cheminImage) { this.cheminImage = cheminImage; }
}

