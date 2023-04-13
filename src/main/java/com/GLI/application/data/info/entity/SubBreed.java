package com.GLI.application.data.info.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubBreed {
    private int id;
    private int breedId;
    private String subBreed;

    public SubBreed(int id, int breedId, String subBreed) {
        this.id = id;
        this.breedId = breedId;
        this.subBreed = subBreed;
    }
    public SubBreed() {
    }
}
