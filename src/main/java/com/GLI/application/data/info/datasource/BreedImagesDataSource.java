package com.GLI.application.data.info.datasource;

import com.GLI.application.data.info.entity.BreedImagesEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BreedImagesDataSource {
    BreedImagesEntity getImages(String breed);

    CompletableFuture<List<String>> getImagesForSubBreed(String breed, String subBreed);
}

