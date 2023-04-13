package com.GLI.application.data.info.mappper;

import com.GLI.application.data.info.entity.SubBreedsEntity;
import com.GLI.application.domain.model.SubBreedsModel;
import com.GLI.common.Mapper;

public class SubBreedsModelToSubBreedsEntityMapper extends Mapper<SubBreedsModel, SubBreedsEntity> {

    @Override
    public SubBreedsEntity map(SubBreedsModel value) {
        return null;
    }

    @Override
    public SubBreedsModel reverseMap(SubBreedsEntity value) {
        SubBreedsModel subBreeds = new SubBreedsModel();
        subBreeds.subBreed = value.message;
        return subBreeds;
    }
}
