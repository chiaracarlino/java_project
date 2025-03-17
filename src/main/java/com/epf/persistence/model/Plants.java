package com.epf.persistence.model;

public class Plants {
    private Long id;
    private String name;
    private int health;
    private int damage;
    private int cost;

    // Constructeurs
    public Plants() {}

    public Plants(Long id, String name, int health, int damage, int cost) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.cost = cost;
    }

    public Plants(long id, String nom, int vie, int attaque) {
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }
}