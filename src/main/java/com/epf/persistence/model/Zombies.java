package com.epf.persistence.model;

public class Zombies {
    private Long id;
    private String name;
    private int health;
    private Long mapId;

    // 🟢 Constructeurs
    public Zombies() {
    }

    public Zombies(Long id, String name, int health, Long mapId) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.mapId = mapId;
    }

    public Zombies(long id, String nom, int vie, int attaque, long mapId) {
    }

    // 🟢 Getters et Setters
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
        }

}

