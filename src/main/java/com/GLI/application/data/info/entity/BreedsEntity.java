package com.GLI.application.data.info.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@Setter
public class BreedsEntity {
    @JsonProperty("message")
    private Map<String, List<String>> breeds = new HashMap<>();
    @JsonProperty
    public String status;
}
