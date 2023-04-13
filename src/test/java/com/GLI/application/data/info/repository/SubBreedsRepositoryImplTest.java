package com.GLI.application.data.info.repository;

import com.GLI.application.data.info.datasource.SubBreedsDataSource;
import com.GLI.application.data.info.entity.SubBreedsEntity;
import com.GLI.application.domain.model.SubBreedsModel;
import com.GLI.common.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

class SubBreedsRepositoryImplTest {

    private final String BREED_NAME = "bulldog";
    private final String DATA_STATUS = "success";
    private final String DATA_SUBBREED_1 = "french";
    private final String DATA_SUBBREED_2 = "english";
    private final String DATA_SUBBREED_3 = "boston";

    private SubBreedsRepositoryImpl sut;

    private SubBreedsEntity subBreedsEntityMock;
    private SubBreedsModel subBreedsModelMock;

    @Mock
    private SubBreedsDataSource subBreedsDataSourceMock;

    @Mock
    private Mapper<SubBreedsModel, SubBreedsEntity> subBreedsModelSubBreedsEntityMapperMock;

    @Mock
    private DataSource dataSource;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        sut = new SubBreedsRepositoryImpl(subBreedsModelSubBreedsEntityMapperMock, subBreedsDataSourceMock, dataSource);

        subBreedsEntityMock = new SubBreedsEntity();
        subBreedsEntityMock.status = DATA_STATUS;
        subBreedsEntityMock.message = new ArrayList<>();
        subBreedsEntityMock.message.add(DATA_SUBBREED_1);
        subBreedsEntityMock.message.add(DATA_SUBBREED_2);
        subBreedsEntityMock.message.add(DATA_SUBBREED_3);

        subBreedsModelMock = new SubBreedsModel();
        subBreedsModelMock.subBreed = new ArrayList<>();
        subBreedsModelMock.subBreed.add(DATA_SUBBREED_1);
        subBreedsModelMock.subBreed.add(DATA_SUBBREED_2);
        subBreedsModelMock.subBreed.add(DATA_SUBBREED_3);
    }

    @Test
    public void shouldReturnValidSubBreeds_whenGetSubBreedsIsCalled(){
        when(subBreedsDataSourceMock.getSubBreeds(BREED_NAME)).thenReturn(subBreedsEntityMock);
        when(subBreedsModelSubBreedsEntityMapperMock.reverseMap(subBreedsEntityMock)).thenReturn(subBreedsModelMock);

        SubBreedsModel subBreeds = sut.getSubBreeds(BREED_NAME);

        Assertions.assertEquals(subBreedsModelMock.subBreed.stream().count(), subBreeds.subBreed.stream().count());
        Assertions.assertEquals(subBreedsModelMock.subBreed.stream().findFirst(),subBreeds.subBreed.stream().findFirst()) ;
    }
}