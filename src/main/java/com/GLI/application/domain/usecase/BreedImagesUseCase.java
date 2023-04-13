package com.GLI.application.domain.usecase;

import com.GLI.application.domain.model.BreedImagesModel;

import java.util.List;

public interface BreedImagesUseCase {
    BreedImagesModel getBreedImages(String breed);

    List<String> getSubBreedImages(String breed, String subBreed);
}
