package com.GLI.application.data.info.datasource;

import com.GLI.application.data.info.entity.BreedImagesEntity;
import com.GLI.configuration.BreedProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

class BreedImagesDataSourceImplTest {

    private final String BREED_NAME = "bulldog";
    private final String DATA_STATUS = "success";
    private final String DATA_IMAGES_1 = "https://images.dog.ceo/breeds/bulldog-boston/20200710_175933.jpg";
    private final String DATA_IMAGES_2 = "https://images.dog.ceo/breeds/bulldog-boston/20200710_175944.jpg";
    private final String DATA_IMAGES_3 = "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10380.jpg";

    private BreedImagesDataSourceImpl sut;
    private BreedImagesEntity breedImagesEntityMock;

    @Mock
    private RestTemplate restOperationsMock;

    @Mock
    private BreedProperties breedPropertiesMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new BreedImagesDataSourceImpl(restOperationsMock, breedPropertiesMock);

        breedImagesEntityMock = new BreedImagesEntity();
        breedImagesEntityMock.status = DATA_STATUS;
        breedImagesEntityMock.message = new ArrayList<>();
        breedImagesEntityMock.message.add(DATA_IMAGES_1);
        breedImagesEntityMock.message.add(DATA_IMAGES_2);
        breedImagesEntityMock.message.add(DATA_IMAGES_3);
    }

    @Test
    public void shouldReturnBreedImagesEntity_whenRequestIsExecuted(){
        when(restOperationsMock.getForObject(
                breedPropertiesMock.getBreedImagesURL(BREED_NAME),
                BreedImagesEntity.class
                )
        ).thenReturn(breedImagesEntityMock);

        BreedImagesEntity breedImagesEntity = sut.getImages(BREED_NAME);

        Assertions.assertEquals(breedImagesEntityMock.message.stream().count(), breedImagesEntity.message.stream().count());
        Assertions.assertEquals(DATA_STATUS, breedImagesEntity.status);
    }
}