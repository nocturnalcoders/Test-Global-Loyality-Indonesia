package com.GLI.application.domain.usecase;

import com.GLI.application.data.info.entity.Breed;
import com.GLI.application.data.info.repository.BreedImagesRepository;
import com.GLI.application.data.info.repository.BreedsRepository;
import com.GLI.application.data.info.repository.SubBreedsRepository;
import com.GLI.application.domain.model.BreedImagesModel;
import com.GLI.application.domain.model.BreedModel;
import com.GLI.application.domain.model.BreedsModel;
import com.GLI.application.domain.model.SubBreedsModel;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class BreedUseCaseImpl implements BreedUseCase {

    private final BreedsRepository breedsRepository;
    private final SubBreedsRepository subBreedsRepository;
    private final BreedImagesRepository breedImagesRepository;

    public BreedUseCaseImpl(
            BreedsRepository breedsRepository,
            SubBreedsRepository subBreedsRepository,
            BreedImagesRepository breedImagesRepository
    ){
        this.breedsRepository = breedsRepository;
        this.subBreedsRepository = subBreedsRepository;
        this.breedImagesRepository = breedImagesRepository;
    }

    @Override
    public BreedModel getInfo(String breed) {
        SubBreedsModel subBreeds = this.subBreedsRepository.getSubBreeds(breed);
        BreedImagesModel breedImages = this.breedImagesRepository.getBreedImages(breed);
        return new BreedModel(breed, subBreeds, breedImages);
    }

    @Override
    public List<BreedsModel> getAllBreeds() {
        return this.breedsRepository.getAllBreeds();
    }

    @Override
    public BreedModel create(String breed, String subBreed, String imgUrl) throws Exception {
        var newBreed = this.breedsRepository.create(breed);
        var newSB = this.subBreedsRepository.create(newBreed.getId(), subBreed);
        var result = new BreedModel();
        result.setBreed(breed);
        result.setSubBreed(List.of(newSB.getSubBreed()));
        result.setImages(List.of(imgUrl));
        return result;
    }

    @Override
    public BreedModel create(String breed, String subBreed, MultipartFile imgFile) throws Exception {
        var newBreed = this.breedsRepository.create(breed);
        var newSB = this.subBreedsRepository.create(newBreed.getId(), subBreed);
        var img = this.breedImagesRepository.create(newBreed.getId(), newSB.getId(), imgFile);
        return this.create(breed, subBreed, img.getUrl());
    }

    @Override
    public Breed update(String breed) throws Exception {
        return this.breedsRepository.update(breed);
    }

    @Override
    public Breed delete(String breed) throws Exception {
        return this.breedsRepository.delete(breed);
    }

    @Override
    public Breed get(String breed) throws Exception {
        return this.breedsRepository.get(breed);
    }

    @Override
    public Breed get(int id) throws Exception {
        return this.breedsRepository.get(id);
    }

    @Override
    public List<Breed> getAll() throws Exception {
        return this.breedsRepository.getAll();
    }
}
