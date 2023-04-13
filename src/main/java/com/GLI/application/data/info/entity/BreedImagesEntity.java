package com.GLI.application.data.info.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BreedImagesEntity {
    @JsonProperty
    public List<String> message;
    @JsonProperty
    public String status;
}
