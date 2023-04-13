package com.GLI.application.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class BreedModel {
    public String breed;
    public List<String> subBreed;
    public List<String> images;

    public BreedModel(){
        this.breed = "";
        this.subBreed = new ArrayList<>();
        this.images = new ArrayList<> ();
    }

    public BreedModel(String breed, SubBreedsModel subBreeds, BreedImagesModel breedImages){
        this.breed = breed;
        this.subBreed = subBreeds.subBreed;
        this.images = breedImages.images;
    }
}
