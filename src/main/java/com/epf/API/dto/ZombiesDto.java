package com.epf.API.dto;

public class ZombiesDto {
    private Integer id_zombie;
    private String nom;
    private Integer point_de_vie;        
    private Integer degat_attaque;         
    private Integer id_map;           
    private Double attaque_par_seconde;    
    private Double vitesse_de_deplacement; 
    private String chemin_image;

    public ZombiesDto() {}

    public ZombiesDto(Integer id_zombie, String nom, Integer point_de_vie, Integer degat_attaque,
                   Integer id_map, Double attaque_par_seconde, 
                   Double vitesse_de_deplacement, String chemin_image) {
        this.id_zombie = id_zombie;
        this.nom = nom;
        this.point_de_vie = point_de_vie;
        this.degat_attaque = degat_attaque;
        this.id_map = id_map;
        this.attaque_par_seconde = attaque_par_seconde;
        this.vitesse_de_deplacement = vitesse_de_deplacement;
        this.chemin_image = chemin_image;
    }

    public Integer getId_zombie() { return id_zombie; }
    public void setId_zombie(Integer id_zombie) { this.id_zombie = id_zombie; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getPoint_de_vie() { return point_de_vie; }
    public void setPoint_de_vie(Integer point_de_vie) { this.point_de_vie = point_de_vie; }

    public Integer getDegat_attaque() { return degat_attaque; }
    public void setDegat_attaque(Integer degat_attaque) { this.degat_attaque = degat_attaque; }

    public Integer getId_map() { return id_map; }
    public void setId_map(Integer id_map) { this.id_map = id_map; }

    public Double getAttaque_par_seconde() { return attaque_par_seconde; }
    public void setAttaque_par_seconde(Double attaque_par_seconde) { this.attaque_par_seconde = attaque_par_seconde; }

    public Double getVitesse_de_deplacement() { return vitesse_de_deplacement; }
    public void setVitesse_de_deplacement(Double vitesse_de_deplacement) { this.vitesse_de_deplacement = vitesse_de_deplacement; }

    public String getChemin_image() { return chemin_image; }
    public void setChemin_image(String chemin_image) { this.chemin_image = chemin_image; }
}


