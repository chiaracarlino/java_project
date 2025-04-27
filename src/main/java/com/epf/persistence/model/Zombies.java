package com.epf.persistence.model;

public class Zombies {
    private Integer id;
    private String nom;
    private Integer pointDeVie;           // NOT NULL int unsigned
    private Integer degatAttaque;         // NOT NULL int unsigned
    private Integer idMap;            // NULL allowed
    private Double attaqueParSeconde;    // decimal(5,2)
    private Double vitesseDeDeplacement; // decimal(5,2)
    private String cheminImage;       // varchar(255) NULL allowed
    private Maps map;                 // Added Map relationship

    public Zombies() {
    }

    public Zombies(Integer id, String nom, Integer pointDeVie, Integer degatAttaque, Integer idMap,
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

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getPointDeVie() { return pointDeVie; }
    public void setPointDeVie(Integer pointDeVie) { this.pointDeVie = pointDeVie; }

    public Integer getDegatAttaque() { return degatAttaque; }
    public void setDegatAttaque(Integer degatAttaque) { this.degatAttaque = degatAttaque; }

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

    public Integer getIdZombie() { return id; }
    public void setIdZombie(Integer idZombie) { this.id = idZombie; }

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

