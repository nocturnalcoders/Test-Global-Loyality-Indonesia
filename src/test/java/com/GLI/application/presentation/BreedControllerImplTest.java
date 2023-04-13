package com.GLI.application.presentation;

import com.GLI.application.domain.model.BreedModel;
import com.GLI.application.domain.usecase.BreedImagesUseCase;
import com.GLI.application.domain.usecase.BreedUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

class BreedControllerImplTest {

    private BreedController sut;
    private BreedModel breedModelMock;
    private final String BREED_NAME = "bulldog";

    @Mock
    private BreedUseCase breedUseCaseMock;

    @Mock
    private BreedImagesUseCase breedImagesUseCaseMock;

    @BeforeEach
    void setUp() {
        // NOTE: Coloque estos atributos como variables para que el IDE no me reclamara (Field can be covered to a local variable)
        String DATA_IMAGES_1 = "https://images.dog.ceo/breeds/bulldog-boston/20200710_175933.jpg";
        String DATA_IMAGES_2 = "https://images.dog.ceo/breeds/bulldog-boston/20200710_175944.jpg";
        String DATA_IMAGES_3 = "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10380.jpg";

        String DATA_SUBBREED_1 = "french";
        String DATA_SUBBREED_2 = "english";
        String DATA_SUBBREED_3 = "boston";


        MockitoAnnotations.openMocks(this);
        sut = new BreedControllerImpl(breedUseCaseMock, breedImagesUseCaseMock);

        breedModelMock = new BreedModel();
        breedModelMock.breed = BREED_NAME;
        breedModelMock.images = new ArrayList<>();
        breedModelMock.images.add(DATA_IMAGES_1);
        breedModelMock.images.add(DATA_IMAGES_2);
        breedModelMock.images.add(DATA_IMAGES_3);
        breedModelMock.subBreed = new ArrayList<>();
        breedModelMock.subBreed.add(DATA_SUBBREED_1);
        breedModelMock.subBreed.add(DATA_SUBBREED_2);
        breedModelMock.subBreed.add(DATA_SUBBREED_3);
    }

    @Test
    public void shouldReturnValidBreedModel_whenIsCalled() {
        when(breedUseCaseMock.getInfo(BREED_NAME)).thenReturn(breedModelMock);

        ResponseEntity<BreedModel> responseEntity = (ResponseEntity<BreedModel>) sut.detail(BREED_NAME);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
    }
}