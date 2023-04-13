package com.GLI.application.domain.usecase;

import com.GLI.application.data.info.entity.Breed;
import com.GLI.application.domain.model.BreedModel;
import com.GLI.application.domain.model.BreedsModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BreedUseCase {
    BreedModel getInfo(String breed);

    List<BreedsModel> getAllBreeds();

    BreedModel create(String breed, String subBreed, String imgUrl) throws Exception;
    BreedModel create(String breed, String subBreed, MultipartFile imgFile) throws Exception;

    Breed update(String breed) throws Exception;

    Breed delete(String breed) throws Exception;

    Breed get(String breed) throws Exception;

    Breed get(int id) throws Exception;

    List<Breed> getAll() throws Exception;
}
