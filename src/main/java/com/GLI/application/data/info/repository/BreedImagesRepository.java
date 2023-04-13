package com.GLI.application.data.info.repository;

import com.GLI.application.data.info.entity.Image;
import com.GLI.application.domain.model.BreedImagesModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BreedImagesRepository {
    BreedImagesModel getBreedImages(String breed);

    CompletableFuture<List<String>> getSubBreedImages(String breed, String subBreed);

    Image create(int breedId, int subBreedId, String imageUrl) throws SQLException;

    Image create(int breedId, int subBreedId, MultipartFile imageFile) throws SQLException, IOException;

    Image update(int breedId, int subBreedId, String imageUrl) throws SQLException;
    Image update(int breedId, int subBreedId, MultipartFile ImageFile) throws SQLException;

}
