package com.GLI.application.data.info.mapper;

import com.GLI.application.data.info.entity.BreedImagesEntity;
import com.GLI.application.data.info.mappper.BreedImagesModelToBreedImagesEntityMapper;
import com.GLI.application.domain.model.BreedImagesModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BreedImagesModelToBreedImagesEntityMapperTest {

    private BreedImagesModelToBreedImagesEntityMapper sut;
    private BreedImagesEntity breedImagesEntityMock;
    private final String DATA_STATUS = "success";
    private final String DATA_IMAGES_1 = "https://images.dog.ceo/breeds/bulldog-boston/20200710_175933.jpg";
    private final String DATA_IMAGES_2 = "https://images.dog.ceo/breeds/bulldog-boston/20200710_175944.jpg";
    private final String DATA_IMAGES_3 = "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10380.jpg";

    @BeforeEach
    void setUp() {
        sut = new BreedImagesModelToBreedImagesEntityMapper();

        breedImagesEntityMock = new BreedImagesEntity();
        breedImagesEntityMock.status = DATA_STATUS;
        breedImagesEntityMock.message = new ArrayList<>();
        breedImagesEntityMock.message.add(DATA_IMAGES_1);
        breedImagesEntityMock.message.add(DATA_IMAGES_2);
        breedImagesEntityMock.message.add(DATA_IMAGES_3);
    }

    @Test
    public void shouldReturnValidBreedImages_whenReverseMapIsCalled(){
        BreedImagesModel breedImages = sut.reverseMap(breedImagesEntityMock);

        Assertions.assertEquals(breedImagesEntityMock.message.stream().count(), breedImages.images.stream().count());
    }
}