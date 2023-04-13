package com.GLI.application.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import java.io.Serializable;

@Getter
@Setter
public class CreateDogRequest implements Serializable {
    @JsonProperty
    @NotBlank(message = "Breed cannot be blank")
    private String breed;
    @JsonProperty
    @NotBlank(message = "Sub Breed cannot be blank")
    private String sub_breed;
    @JsonProperty
    @URL(message = "Image URL must be valid")
    private String image_url;
}
