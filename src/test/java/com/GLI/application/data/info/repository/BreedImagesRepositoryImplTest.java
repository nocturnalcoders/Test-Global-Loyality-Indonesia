package com.GLI.application.data.info.repository;

import com.GLI.application.data.info.datasource.BreedImagesDataSource;
import com.GLI.application.data.info.entity.BreedImagesEntity;
import com.GLI.application.domain.model.BreedImagesModel;
import com.GLI.common.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

class BreedImagesRepositoryImplTest {

    private final String BREED_NAME = "bulldog";
    private final String DATA_STATUS = "success";
    private final String DATA_IMAGES_1 = "https://images.dog.ceo/breeds/bulldog-boston/20200710_175933.jpg";
    private final String DATA_IMAGES_2 = "https://images.dog.ceo/breeds/bulldog-boston/20200710_175944.jpg";
    private final String DATA_IMAGES_3 = "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10380.jpg";

    private BreedImagesRepositoryImpl sut;

    private BreedImagesEntity breedImagesEntityMock;
    private BreedImagesModel breedImagesModelMock;

    @Mock
    private BreedImagesDataSource breedImagesDataSourceMock;

    @Mock
    private Mapper<BreedImagesModel, BreedImagesEntity> breedImagesModelBreedImagesEntityMapperMock;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        sut = new BreedImagesRepositoryImpl(breedImagesModelBreedImagesEntityMapperMock, breedImagesDataSourceMock, null);

        breedImagesEntityMock = new BreedImagesEntity();
        breedImagesEntityMock.status = DATA_STATUS;
        breedImagesEntityMock.message = new ArrayList<>();
        breedImagesEntityMock.message.add(DATA_IMAGES_1);
        breedImagesEntityMock.message.add(DATA_IMAGES_2);
        breedImagesEntityMock.message.add(DATA_IMAGES_3);

        breedImagesModelMock = new BreedImagesModel();
        breedImagesModelMock.images = new ArrayList<>();
        breedImagesModelMock.images.add(DATA_IMAGES_1);
        breedImagesModelMock.images.add(DATA_IMAGES_2);
        breedImagesModelMock.images.add(DATA_IMAGES_3);
    }

    @Test
    public void shouldReturnValidBreedImages_whenGetBreedImagesIsCalled(){
        when(breedImagesDataSourceMock.getImages(BREED_NAME)).thenReturn(breedImagesEntityMock);
        when(breedImagesModelBreedImagesEntityMapperMock.reverseMap(breedImagesEntityMock)).thenReturn(breedImagesModelMock);

        BreedImagesModel breedImages = sut.getBreedImages(BREED_NAME);

        Assertions.assertEquals(breedImagesModelMock.images.stream().count(), breedImages.images.stream().count());
        Assertions.assertEquals(breedImagesModelMock.images.stream().findFirst(),breedImages.images.stream().findFirst()) ;
    }
}