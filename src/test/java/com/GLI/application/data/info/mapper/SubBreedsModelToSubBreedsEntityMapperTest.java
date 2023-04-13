package com.GLI.application.data.info.mapper;

import com.GLI.application.data.info.entity.SubBreedsEntity;
import com.GLI.application.data.info.mappper.SubBreedsModelToSubBreedsEntityMapper;
import com.GLI.application.domain.model.SubBreedsModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SubBreedsModelToSubBreedsEntityMapperTest {

    private SubBreedsModelToSubBreedsEntityMapper sut;
    private SubBreedsEntity subBreedsEntityMock;
    private final String DATA_STATUS = "success";
    private final String DATA_SUBBREED_1 = "french";
    private final String DATA_SUBBREED_2 = "english";
    private final String DATA_SUBBREED_3 = "boston";

    @BeforeEach
    void setUp() {
        sut = new SubBreedsModelToSubBreedsEntityMapper();

        subBreedsEntityMock = new SubBreedsEntity();
        subBreedsEntityMock.status = DATA_STATUS;
        subBreedsEntityMock.message = new ArrayList<>();
        subBreedsEntityMock.message.add(DATA_SUBBREED_1);
        subBreedsEntityMock.message.add(DATA_SUBBREED_2);
        subBreedsEntityMock.message.add(DATA_SUBBREED_3);
    }

    @Test
    public void shouldReturnValidSubBreeds_whenReverseMapIsCalled(){
        SubBreedsModel subBreeds = sut.reverseMap(subBreedsEntityMock);

        Assertions.assertEquals(subBreedsEntityMock.message.stream().count(), subBreeds.subBreed.stream().count());
    }
}