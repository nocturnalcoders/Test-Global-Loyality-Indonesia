package com.GLI.application.data.info.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {
    private int id;
    private String url;
    private int breed_id;
    private int sub_breed_id;
    public Image(int id, String url, int breed_id, int sub_breed_id) {
        this.id = id;
        this.url = url;
        this.breed_id = breed_id;
        this.sub_breed_id = sub_breed_id;
    }
    public Image() {
    }
}
