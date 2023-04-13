package com.GLI.configuration;

import org.springframework.beans.factory.annotation.Value;

public class BreedProperties {

    @Value("${dog-ceo_api.base_url}")
    private String baseURL;
    @Value("${dog-ceo_api.endpoints.breeds_list}")
    private String breedsListEndpoint;
    @Value("${dog-ceo_api.endpoints.sub_breed}")
    private String subBreedEndPoint;
    @Value("${dog-ceo_api.endpoints.images_breed}")
    private String imagesBreedEndPoint;

    @Value("${dog-ceo_db.schema_path}")
    private String schemaPath;

    public String getBreedsURL() {
        return this.baseURL.concat(breedsListEndpoint);
    }
    public String getSubBreedsURL(String breed) {
        return this.baseURL.concat(subBreedEndPoint).replace("{breed_name}", breed);
    }
    public String getBreedImagesURL(String breed) {
        return this.baseURL.concat(imagesBreedEndPoint).replace("{breed_name}", breed);
    }

    public String getSchemaPath() {
        return this.schemaPath;
    }
}
