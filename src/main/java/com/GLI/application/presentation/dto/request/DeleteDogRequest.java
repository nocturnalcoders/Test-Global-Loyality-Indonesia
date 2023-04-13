package com.GLI.application.presentation.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class DeleteDogRequest implements Serializable {
    private String breed;
    private String sub_breed;
}
