package com.epf.API.dto;

public class PlantsDto {
    private Long id;
    private String name;
    private Integer health;
    private Integer damage;
    private Long mapId;  // ID de la carte à laquelle appartient cette plante

    public PlantsDto() {
    }

    public PlantsDto(Long id, String name, Integer health, Integer damage, Long mapId) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.mapId = mapId;
    }

    public PlantsDto(Long id, String name, int health, int damage) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }
}