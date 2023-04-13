package com.GLI.configuration;

import com.GLI.application.data.info.datasource.*;
import com.GLI.application.data.info.entity.*;
import com.GLI.application.data.info.mappper.*;
import com.GLI.application.data.info.repository.*;
import com.GLI.application.domain.model.*;
import com.GLI.application.domain.usecase.*;
import com.GLI.application.presentation.*;
import com.GLI.common.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * BreedConfiguration
 * Tell us, in which line you use spring boot singleton pattern:
 */
@Configuration
public class BreedConfiguration {

    // Presentation
    @Bean
    BreedController breedDetailController(BreedUseCase breedUseCase, BreedImagesUseCase breedImagesUseCase, DataSource dataSource) {
        return new BreedControllerImpl(breedUseCase, breedImagesUseCase);
    }
    // Domain.useCase

    @Bean
    BreedUseCase breedUseCase(
            BreedsRepository breedsRepository,
            SubBreedsRepository subBreedsRepository,
            BreedImagesRepository breedImagesRepository
    ) {
        return new BreedUseCaseImpl(breedsRepository, subBreedsRepository, breedImagesRepository);
    }

    @Bean
    BreedImagesUseCase breedImagesUseCase(
            BreedImagesRepository breedImagesRepository
    ) {
        return new BreedImagesUseCaseImpl(breedImagesRepository);
    }

    // Data.repository

    @Bean
    BreedsRepository breadsRepository(
            BreedsMapper breadsModelBreadsEntityMapper,
            BreedsDataSource breadsDataSource, DataSource dataSource
    ) throws SQLException {
        return new BreedsRepositoryImpl(breadsModelBreadsEntityMapper, breadsDataSource, dataSource);
    }

    @Bean
    SubBreedsRepository subBreedsRepository(
            Mapper<SubBreedsModel, SubBreedsEntity> subBreedsModelSubBreedsEntityMapper,
            SubBreedsDataSource subBreedsDataSource, DataSource dataSource
    ) throws SQLException {
        return new SubBreedsRepositoryImpl(subBreedsModelSubBreedsEntityMapper, subBreedsDataSource, dataSource);
    }

    @Bean
    BreedImagesRepository breedImagesRepository(
            Mapper<BreedImagesModel, BreedImagesEntity> breedImagesModelBreedImagesEntityMapper,
            BreedImagesDataSource breedImagesDataSource, DataSource dataSource
    ) throws SQLException {
        return new BreedImagesRepositoryImpl(breedImagesModelBreedImagesEntityMapper, breedImagesDataSource, dataSource);
    }

    // Data.mapper
    @Bean
    BreedsMapper breadsModelBreadsEntityMapper() {
        return new BreedsMapper();
    }

    @Bean
    Mapper<SubBreedsModel, SubBreedsEntity> subBreedsModelSubBreedsEntityMapper() {
        return new SubBreedsModelToSubBreedsEntityMapper();
    }

    @Bean
    Mapper<BreedImagesModel, BreedImagesEntity> breedImagesModelBreedImagesEntityMapper() {
        return new BreedImagesModelToBreedImagesEntityMapper();
    }

    // Data.datasource
    @Bean
    public DataSource dataSource(BreedProperties breedProperties) {
        return new EmbeddedDatabaseBuilder()
                .setName("dog_ceo")
                .setType(EmbeddedDatabaseType.H2)
                .addScript(breedProperties.getSchemaPath())
                .build();
    }
    @Bean
    BreedsDataSource breedsDataSource(
            RestTemplate restOperations,
            BreedProperties breedProperties
    ) {
        return new BreedsDataSourceImpl(restOperations, breedProperties);
    }


    @Bean
    SubBreedsDataSource subBreedsDataSource(
            RestTemplate restOperations,
            BreedProperties breedProperties
    ) {
        return new SubBreedsDataSourceImpl(restOperations, breedProperties);
    }

    @Bean
    BreedImagesDataSource breedImagesDataSource(
            RestTemplate restOperations,
            BreedProperties breedProperties
    ) {
        return new BreedImagesDataSourceImpl(restOperations, breedProperties);
    }

    // Configuration

    @Bean
    BreedProperties BreedProperties() {

        return new BreedProperties();
    }

    // FrameWorks

    @Bean
    RestTemplate restOperations() {
        return new RestTemplate();
    }
}
