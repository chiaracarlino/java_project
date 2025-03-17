package com.epf.API.dto;

public class MapsDto {
    private Long id;
    private String name;
    private Integer difficultyLevel;

    public MapsDto() {
    }

    public MapsDto(Long id, String name, Integer difficultyLevel) {
        this.id = id;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
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

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}