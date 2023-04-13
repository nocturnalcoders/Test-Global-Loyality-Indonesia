package com.GLI.application.domain.usecase;

import com.GLI.application.data.info.repository.BreedImagesRepository;
import com.GLI.application.domain.model.BreedImagesModel;
import java.util.ArrayList;
import java.util.List;

public class BreedImagesUseCaseImpl implements BreedImagesUseCase {

    private final BreedImagesRepository breedImagesRepository;

    public BreedImagesUseCaseImpl(BreedImagesRepository breedImagesRepository) {
        this.breedImagesRepository = breedImagesRepository;
    }

    @Override
    public BreedImagesModel getBreedImages(String breed) {
        return this.breedImagesRepository.getBreedImages(breed);
    }

    @Override
    public List<String> getSubBreedImages(String breed, String subBreed) {
        try {
            return this.breedImagesRepository.getSubBreedImages(breed, subBreed).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
