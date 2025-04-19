package com.epf.persistence.model;

public class Maps {
    private Long id;
    private String name;
    private int width;
    private int height;
    private int difficultyLevel;

    // Constructeurs
    public Maps() {}

    public Maps(Long id, String name, int width, int height, int difficultyLevel) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.difficultyLevel = difficultyLevel;
    }

    public Maps(Long id, String name, int difficultyLevel) {
        this.id = id;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.width = 0;
        this.height = 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    private String imagePath;

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

}
