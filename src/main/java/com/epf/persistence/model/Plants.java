package com.epf.persistence.model;

public class Plants {
    private int idPlante;
    private String nom;
    private int pointDeVie;
    private Double attaqueParSeconde;
    private int degatAttaque;
    private int cout;
    private Double soleilParSeconde;
    private String effet;
    private String cheminImage;

    public Plants() {
    }

    public Plants(int idPlante, String nom, int pointDeVie, Double attaqueParSeconde,
                  int degatAttaque, int cout, Double soleilParSeconde, String effet, String cheminImage) {
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

    public int getIdPlante() { return idPlante; }
    public void setIdPlante(int idPlante) { this.idPlante = idPlante; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getPointDeVie() { return pointDeVie; }
    public void setPointDeVie(int pointDeVie) { this.pointDeVie = pointDeVie; }

    public Double getAttaqueParSeconde() { return attaqueParSeconde; }
    public void setAttaqueParSeconde(Double attaqueParSeconde) { this.attaqueParSeconde = attaqueParSeconde; }

    public int getDegatAttaque() { return degatAttaque; }
    public void setDegatAttaque(int degatAttaque) { this.degatAttaque = degatAttaque; }

    public int getCout() { return cout; }
    public void setCout(int cout) { this.cout = cout; }

    public Double getSoleilParSeconde() { return soleilParSeconde; }
    public void setSoleilParSeconde(Double soleilParSeconde) { this.soleilParSeconde = soleilParSeconde; }

    public String getEffet() { return effet; }
    public void setEffet(String effet) { this.effet = effet; }

    public String getCheminImage() { return cheminImage; }
    public void setCheminImage(String cheminImage) { this.cheminImage = cheminImage; }
}
