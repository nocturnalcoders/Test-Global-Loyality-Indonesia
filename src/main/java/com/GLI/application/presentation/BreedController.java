package com.GLI.application.presentation;

import com.GLI.application.presentation.dto.request.CreateDogRequest;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/v1")
@RestController
@Api(description = "API DOGS")
public interface BreedController {
    @GetMapping(value = "/detail/{breed_name}", produces = "application/json")
    ResponseEntity<?> detail(@PathVariable("breed_name") String breedName);

    @GetMapping(value = "/list", produces = "application/json")
    ResponseEntity<?> list();

    @PostMapping(value = "/create", produces = "application/json")
    ResponseEntity<?> create(@Valid @ModelAttribute CreateDogRequest request, @RequestParam("image") MultipartFile imageFile);

    @PutMapping(value = "/update", produces = "application/json")
    ResponseEntity<?> update(@Valid @ModelAttribute CreateDogRequest request, @RequestParam("image") MultipartFile imageFile);

    @DeleteMapping(value = "/delete", produces = "application/json")
    ResponseEntity<?> delete(@PathVariable("breed_name") String breedName);

}
