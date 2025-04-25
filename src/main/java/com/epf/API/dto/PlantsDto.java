package com.epf.API.dto;

import java.math.BigDecimal;

public class PlantsDto {
    private Integer id_plante;
    private String nom;
    private Integer point_de_vie;
    private BigDecimal attaque_par_seconde;  // Changed from Double
    private Integer degat_attaque;
    private Integer cout;
    private BigDecimal soleil_par_seconde;   // Changed from Double
    private String effet;
    private String chemin_image;

    // Constructors
    public PlantsDto() {}

    public PlantsDto(Integer id_plante, String nom, Integer point_de_vie, 
                    BigDecimal attaque_par_seconde, Integer degat_attaque, 
                    Integer cout, BigDecimal soleil_par_seconde, 
                    String effet, String chemin_image) {
        this.id_plante = id_plante;
        this.nom = nom;
        this.point_de_vie = point_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.cout = cout;
        this.soleil_par_seconde = soleil_par_seconde;
        this.effet = effet;
        this.chemin_image = chemin_image;
    }

    public Integer getId_plante() { return id_plante; }
    public void setId_plante(Integer id_plante) { this.id_plante = id_plante; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getPoint_de_vie() { return point_de_vie; }
    public void setPoint_de_vie(Integer point_de_vie) { this.point_de_vie = point_de_vie; }

    public BigDecimal getAttaque_par_seconde() { return attaque_par_seconde; }
    public void setAttaque_par_seconde(BigDecimal attaque_par_seconde) { this.attaque_par_seconde = attaque_par_seconde; }

    public Integer getDegat_attaque() { return degat_attaque; }
    public void setDegat_attaque(Integer degat_attaque) { this.degat_attaque = degat_attaque; }

    public Integer getCout() { return cout; }
    public void setCout(Integer cout) { this.cout = cout; }

    public BigDecimal getSoleil_par_seconde() { return soleil_par_seconde; }
    public void setSoleil_par_seconde(BigDecimal soleil_par_seconde) { this.soleil_par_seconde = soleil_par_seconde; }

    public String getEffet() { return effet; }
    public void setEffet(String effet) { this.effet = effet; }

    public String getChemin_image() { return chemin_image; }
    public void setChemin_image(String chemin_image) { this.chemin_image = chemin_image; }
}
