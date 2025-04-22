package com.epf.API.dto;

public class PlantsDto {
    private Long idPlante;
    private String nom;
    private int pointDeVie;
    private int attaqueParSeconde;
    private int degatAttaque;
    private int cout;
    private int soleilParSeconde;
    private String effet;
    private String cheminImage;

    public PlantsDto() {}

    public PlantsDto(Long idPlante, String nom, int pointDeVie, int attaqueParSeconde, int degatAttaque,
                     int cout, int soleilParSeconde, String effet, String cheminImage) {
        this.idPlante = idPlante;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.cout = cout;
        this.soleilParSeconde = soleilParSeconde;
        this.effet = effet;
        this.cheminImage = cheminImage;
    }

    public Long getIdPlante() {
        return idPlante;
    }

    public void setIdPlante(Long idPlante) {
        this.idPlante = idPlante;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public int getAttaqueParSeconde() {
        return attaqueParSeconde;
    }

    public void setAttaqueParSeconde(int attaqueParSeconde) {
        this.attaqueParSeconde = attaqueParSeconde;
    }

    public int getDegatAttaque() {
        return degatAttaque;
    }

    public void setDegatAttaque(int degatAttaque) {
        this.degatAttaque = degatAttaque;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public int getSoleilParSeconde() {
        return soleilParSeconde;
    }

    public void setSoleilParSeconde(int soleilParSeconde) {
        this.soleilParSeconde = soleilParSeconde;
    }

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
}
