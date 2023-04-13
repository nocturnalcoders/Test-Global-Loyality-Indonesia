package com.GLI.application.data.info.datasource;

import com.GLI.application.data.info.entity.SubBreedsEntity;
import com.GLI.configuration.BreedProperties;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class SubBreedsDataSourceImpl implements SubBreedsDataSource {

    private final RestTemplate restOperations;
    private final BreedProperties breedProperties;

    public SubBreedsDataSourceImpl(
            RestTemplate restOperations,
            BreedProperties breedProperties
    )
    {
        this.restOperations = restOperations;
        this.breedProperties = breedProperties;
    }

    @Override
    public SubBreedsEntity getSubBreeds(String breed)
    {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(2000); // 2k milliseconds
        this.restOperations.setRequestFactory(factory);
        return this.restOperations.getForObject(this.breedProperties.getSubBreedsURL(breed), SubBreedsEntity.class);
    }
}
