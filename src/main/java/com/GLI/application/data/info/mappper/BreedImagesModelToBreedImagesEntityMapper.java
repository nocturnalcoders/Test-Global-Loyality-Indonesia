package com.GLI.application.data.info.mappper;

import com.GLI.application.data.info.entity.BreedImagesEntity;
import com.GLI.application.domain.model.BreedImagesModel;
import com.GLI.common.Mapper;

public class BreedImagesModelToBreedImagesEntityMapper extends Mapper<BreedImagesModel, BreedImagesEntity> {

    @Override
    public BreedImagesEntity map(BreedImagesModel value) {
        return null;
    }

    @Override
    public BreedImagesModel reverseMap(BreedImagesEntity value) {
        BreedImagesModel breedImage = new BreedImagesModel();
        breedImage.images = value.message;

        return breedImage;
    }
}
