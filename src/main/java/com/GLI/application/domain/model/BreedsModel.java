package com.GLI.application.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BreedsModel {
    public String breed;
    public List<String> subBreeds = new ArrayList<>();
    public String fetchImgUrl;
}
