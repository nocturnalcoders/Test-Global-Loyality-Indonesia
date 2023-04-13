package com.GLI.application.presentation;

import com.GLI.application.domain.model.BreedModel;
import com.GLI.application.domain.usecase.BreedImagesUseCase;
import com.GLI.application.domain.usecase.BreedUseCase;
import com.GLI.application.presentation.dto.request.CreateDogRequest;
import com.GLI.application.presentation.dto.response.ModifiedBreedResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BreedControllerImpl implements BreedController {
    private final Logger logInfo = LoggerFactory.getLogger(BreedController.class);
    private final BreedUseCase breedUseCase;
    private final BreedImagesUseCase breedImagesUseCase;

    public BreedControllerImpl(BreedUseCase breedUseCase, BreedImagesUseCase breedImagesUseCase) {
        this.breedUseCase = breedUseCase;
        this.breedImagesUseCase = breedImagesUseCase;
    }

    @Override
    public ResponseEntity<?> detail(@PathVariable("breed_name") String breedName) {
        logInfo.info("Getting detail for breed: {}", breedName);
        BreedModel breedModel = this.breedUseCase.getInfo(breedName);
        breedModel.setBreed(breedName.toLowerCase());
        // If the dog breed is sheepdog or terrier, then you should extract the sub breed, become the parent breed:
        if (breedModel.getBreed().equals("terrier") || breedModel.getBreed().equals("sheepdog")) {
            ModifiedBreedResponse response = new ModifiedBreedResponse();
            for (String sb : breedModel.subBreed) {
                List<String> subBreeds = this.breedImagesUseCase.getSubBreedImages(breedModel.getBreed(), sb);
                response.addBreed(breedModel.getBreed(), sb, subBreeds);
            }
            return new ResponseEntity<>(response.getBreeds(), HttpStatus.OK);
        }
        if (breedModel.getSubBreed() != null && breedModel.getSubBreed().size() > 0) {
            List<String> subBreeds = new ArrayList<>();
            for (String sb : breedModel.getSubBreed()) {
                subBreeds.addAll(this.breedImagesUseCase.getSubBreedImages(breedModel.getBreed(), sb));
            }
            breedModel.setImages(subBreeds);
            breedModel.setSubBreed(breedModel.getSubBreed());
        }
        return new ResponseEntity<>(breedModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> list() {
        logInfo.info("Getting list of breeds");
        var breeds = this.breedUseCase.getAllBreeds();
        return new ResponseEntity<>(breeds, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(@Valid @ModelAttribute CreateDogRequest request, @RequestParam("image") MultipartFile imageFile) {
        logInfo.info("Creating new breed: {}", request.toString());
        try {
            var breed = this.breedUseCase.create(request.getBreed(), request.getSub_breed(), imageFile);
            return new ResponseEntity<>(breed, HttpStatus.OK);
        } catch (Exception e) {
            logInfo.error("Error creating breed: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> update(@Valid @ModelAttribute CreateDogRequest request, @RequestParam("image") MultipartFile imageFile) {
        logInfo.info("Updating breed: {}", request.toString());
        try {
            var breed = this.breedUseCase.create(request.getBreed(), request.getSub_breed(), imageFile);
            return new ResponseEntity<>(breed, HttpStatus.OK);
        } catch (Exception e) {
            logInfo.error("Error updating breed: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> delete(String breedName) {
        logInfo.info("Deleting breed: {}", breedName);
        try {
            this.breedUseCase.delete(breedName);
            return new ResponseEntity<>("Breed deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            logInfo.error("Error deleting breed: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
