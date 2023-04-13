package com.GLI.application.presentation.dto.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifiedBreedResponse {
    private final Map<String, List<String>> breeds;

    public ModifiedBreedResponse() {
        this.breeds = new HashMap<>();
    }

    public void addBreed(String breed, String subBreed, List<String> subBreeds) {
        if(subBreeds == null) {
            subBreeds = new ArrayList<>();
        }
        this.breeds.put(breed.concat("-"+subBreed), subBreeds);
    }

    public Map<String, List<String>> getBreeds() {
        return this.breeds;
    }
}

