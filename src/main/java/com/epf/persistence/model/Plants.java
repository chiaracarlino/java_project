package com.epf.persistence.model;

import java.math.BigDecimal;

public class Plants {
    private int idPlante;
    private String nom;
    private int pointDeVie;
    private BigDecimal attaqueParSeconde;  // Changed from int
    private int degatAttaque;
    private int cout;
    private BigDecimal soleilParSeconde;   // Changed from int
    private String effet;
    private String cheminImage;

    // Constructeur vide
    public Plants() {
    }

    // Constructeur complet
    public Plants(int idPlante, String nom, int pointDeVie, BigDecimal attaqueParSeconde,
                  int degatAttaque, int cout, BigDecimal soleilParSeconde, String effet, String cheminImage) {
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

    public int getIdPlante() {
        return idPlante;
    }

    public void setIdPlante(int idPlante) {
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

    public BigDecimal getAttaqueParSeconde() {
        return attaqueParSeconde;
    }

    public void setAttaqueParSeconde(BigDecimal attaqueParSeconde) {
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

    public BigDecimal getSoleilParSeconde() {
        return soleilParSeconde;
    }

    public void setSoleilParSeconde(BigDecimal soleilParSeconde) {
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
