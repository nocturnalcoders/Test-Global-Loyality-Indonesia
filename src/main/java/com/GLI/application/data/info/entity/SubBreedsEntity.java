package com.GLI.application.data.info.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class SubBreedsEntity {
    @JsonProperty
    public List<String> message = new ArrayList<>();
    @JsonProperty
    public String status;
}
