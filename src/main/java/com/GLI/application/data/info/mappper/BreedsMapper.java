package com.GLI.application.data.info.mappper;

import com.GLI.application.data.info.entity.BreedsEntity;
import com.GLI.application.domain.model.BreedsModel;

import java.util.ArrayList;
import java.util.List;

public class BreedsMapper {

    public List<BreedsModel> reverseMap(BreedsEntity value) {
        ArrayList<BreedsModel> breedsModels = new ArrayList<>();
        for (String breed : value.getBreeds().keySet()) {
            BreedsModel breedsModel = new BreedsModel();
            breedsModel.setBreed(breed);
            breedsModel.setSubBreeds(value.getBreeds().get(breed));
            breedsModel.setFetchImgUrl("https://dog.ceo/api/breed/" + breed + "/{sub_breed}/images");
            breedsModels.add(breedsModel);
        }
        return breedsModels;
    }
}
