package com.epf.persistence.model;

public class Zombies {
    private int id;
    private String nom;
    private int pointDeVie;           // NOT NULL int unsigned
    private int degatAttaque;         // NOT NULL int unsigned
    private Integer idMap;            // NULL allowed
    private Double attaqueParSeconde;    // decimal(5,2)
    private Double vitesseDeDeplacement; // decimal(5,2)
    private String cheminImage;       // varchar(255) NULL allowed
    private Maps map;                 // Added Map relationship

    public Zombies() {
    }

    public Zombies(int id, String nom, int pointDeVie, int degatAttaque, Integer idMap,
                   Double attaqueParSeconde, Double vitesseDeDeplacement, String cheminImage) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.degatAttaque = degatAttaque;
        this.idMap = idMap;
        this.attaqueParSeconde = attaqueParSeconde;
        this.vitesseDeDeplacement = vitesseDeDeplacement;
        this.cheminImage = cheminImage;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getPointDeVie() { return pointDeVie; }
    public void setPointDeVie(int pointDeVie) { this.pointDeVie = pointDeVie; }

    public int getDegatAttaque() { return degatAttaque; }
    public void setDegatAttaque(int degatAttaque) { this.degatAttaque = degatAttaque; }

    public Integer getIdMap() { return idMap; }
    public void setIdMap(Integer idMap) { this.idMap = idMap; }

    public Double getAttaqueParSeconde() { return attaqueParSeconde; }
    public void setAttaqueParSeconde(Double attaqueParSeconde) { 
        this.attaqueParSeconde = attaqueParSeconde; 
    }

    public Double getVitesseDeDeplacement() { return vitesseDeDeplacement; }
    public void setVitesseDeDeplacement(Double vitesseDeDeplacement) { 
        this.vitesseDeDeplacement = vitesseDeDeplacement; 
    }

    public String getCheminImage() { return cheminImage; }
    public void setCheminImage(String cheminImage) { this.cheminImage = cheminImage; }

    public Maps getMap() { return map; }
    public void setMap(Maps map) { this.map = map; }

    public int getIdZombie() { return id; }
    public void setIdZombie(int idZombie) { this.id = idZombie; }

    @Override
    public String toString() {
        return "Zombies{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pointDeVie=" + pointDeVie +
                ", degatAttaque=" + degatAttaque +
                ", idMap=" + idMap +
                ", attaqueParSeconde=" + attaqueParSeconde +
                ", vitesseDeDeplacement=" + vitesseDeDeplacement +
                ", cheminImage='" + cheminImage + '\'' +
                '}';
    }
}

