package com.GLI.application.data.info.datasource;

import com.GLI.application.data.info.entity.BreedsEntity;
import com.GLI.configuration.BreedProperties;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class BreedsDataSourceImpl implements BreedsDataSource {
    private final RestTemplate restOperations;
    private final BreedProperties breedProperties;

    public BreedsDataSourceImpl(RestTemplate restOperations, BreedProperties breedProperties) {
        this.restOperations = restOperations;
        this.breedProperties = breedProperties;
    }

    @Override
    public BreedsEntity getAllBreeds() {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000); // 5k milliseconds
        this.restOperations.setRequestFactory(factory);
        return this.restOperations.getForObject(this.breedProperties.getBreedsURL(), BreedsEntity.class);
    }
}
