package com.GLI.application.data.info.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Breed {
    private int id;
    private String breed;

    public Breed(int id, String breed) {
        this.id = id;
        this.breed = breed;
    }
    public Breed() {
    }
}
