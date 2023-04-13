package com.GLI.application.data.info.repository;

import com.GLI.application.data.info.entity.Breed;
import com.GLI.application.domain.model.BreedsModel;

import java.sql.SQLException;
import java.util.List;

public interface BreedsRepository {
    List<BreedsModel> getAllBreeds();

    Breed create(String breed) throws SQLException;

    Breed update(String breed) throws SQLException;

    Breed delete(String breed) throws SQLException;

    Breed get(String breed) throws SQLException;

    Breed get(int id) throws SQLException;

    Breed get(Breed breed) throws SQLException;

    List<Breed> getAll() throws SQLException;
}
