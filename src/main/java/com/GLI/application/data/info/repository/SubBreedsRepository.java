package com.GLI.application.data.info.repository;

import com.GLI.application.data.info.entity.SubBreed;
import com.GLI.application.domain.model.SubBreedsModel;

import java.sql.SQLException;

public interface SubBreedsRepository {
    SubBreedsModel getSubBreeds(String breed);

    SubBreed create(int breedId, String subBreed) throws SQLException;

    SubBreed update(String subBreed) throws SQLException;
    SubBreed update(int breedId, String subBreed) throws SQLException;
    SubBreed delete(String subBreed) throws SQLException;

    SubBreed delete(int breedId, String subBreed) throws SQLException;


}
