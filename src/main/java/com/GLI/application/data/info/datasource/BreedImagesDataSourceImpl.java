package com.GLI.application.data.info.datasource;

import com.GLI.application.data.info.entity.BreedImagesEntity;
import com.GLI.configuration.BreedProperties;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BreedImagesDataSourceImpl implements BreedImagesDataSource {

    private final RestTemplate restOperations;
    private final BreedProperties breedProperties;

    public BreedImagesDataSourceImpl(
            RestTemplate restOperations,
            BreedProperties breedProperties
    ){
        this.restOperations = restOperations;
        this.breedProperties = breedProperties;
    }

    @Override
    public BreedImagesEntity getImages(String breed){
        var endPoint = this.breedProperties.getBreedImagesURL(breed);
        return this.restOperations.getForObject(endPoint, BreedImagesEntity.class);
    }

    @Override
    public CompletableFuture<List<String>> getImagesForSubBreed(String breed, String subBreed) {
        return CompletableFuture.supplyAsync(() -> {
            var resp = this.getImages(breed.concat("/").concat(subBreed));
            assert resp != null;
            return resp.message;
        });
    }
}

