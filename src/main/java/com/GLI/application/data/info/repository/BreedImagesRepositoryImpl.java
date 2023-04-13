package com.GLI.application.data.info.repository;

import com.GLI.application.data.info.datasource.BreedImagesDataSource;
import com.GLI.application.data.info.entity.BreedImagesEntity;
import com.GLI.application.data.info.entity.Image;
import com.GLI.application.domain.model.BreedImagesModel;
import com.GLI.common.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BreedImagesRepositoryImpl implements BreedImagesRepository {

    private final Mapper<BreedImagesModel, BreedImagesEntity> breedImagesModelBreedImagesRepositoryMapper;
    private final BreedImagesDataSource breedImagesDataSource;
    protected final Connection connection;

    public BreedImagesRepositoryImpl(
            Mapper<BreedImagesModel, BreedImagesEntity> breedImagesModelBreedImagesRepositoryMapper,
            BreedImagesDataSource breedImagesDataSource, DataSource dataSource
    ) throws SQLException {
        this.breedImagesModelBreedImagesRepositoryMapper = breedImagesModelBreedImagesRepositoryMapper;
        this.breedImagesDataSource = breedImagesDataSource;
        this.connection = dataSource.getConnection();
    }

    @Override
    public BreedImagesModel getBreedImages(String breed) {
        BreedImagesEntity breedImagesEntity = this.breedImagesDataSource.getImages(breed);
        return this.breedImagesModelBreedImagesRepositoryMapper.reverseMap(breedImagesEntity);
    }

    @Override
    public CompletableFuture<List<String>> getSubBreedImages(String breed, String subBreed) {
        return breedImagesDataSource.getImagesForSubBreed(breed, subBreed);
    }

    @Override
    public Image create(int breedId, int subBreedId, String imageUrl) throws SQLException {
        var insertStatement = this.connection.prepareStatement("INSERT INTO images (breed_id, sub_breed_id, url) VALUES (" + breedId + ", " + subBreedId + ", '" + imageUrl + "')", new String[]{"id"});
        var createResult = insertStatement.executeUpdate();
        if (createResult == 0) {
            throw new SQLException("Failed to create image");
        }
        var generatedKeys = insertStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return new Image(generatedKeys.getInt(1), imageUrl, breedId, subBreedId);
        } else {
            generatedKeys.close();
            throw new SQLException("Failed to create image");
        }

    }

    @Override
    public Image create(int breedId, int subBreedId, MultipartFile imageFile) throws SQLException, IOException {
        File file;
        try {
            file = ResourceUtils.getFile("classpath:application.properties");
            var path = Path.of(file.getAbsolutePath().replace(file.getName(), "") + "/static");
            if(!Files.exists(path)) {
                Files.createDirectories(path);
            }
            imageFile.transferTo(new File(path + "/" +imageFile.getOriginalFilename()));
        } catch (IOException e) {
            file = new ClassPathResource("application.properties").getFile();
            var path = Path.of(file.getAbsolutePath().replace(file.getName(), ""),  "/static");
            if(!Files.exists(path)) {
                Files.createDirectories(path);
            }
            imageFile.transferTo(new File(path+ "/" + imageFile.getOriginalFilename()));
        }
        return this.create(breedId, subBreedId, "{baseURL}/images/" + imageFile.getOriginalFilename());
    }

    @Override
    public Image update(int breedId, int subBreedId, String imageUrl) throws SQLException {
        return null;
    }

    @Override
    public Image update(int breedId, int subBreedId, MultipartFile ImageFile) throws SQLException {
        return null;
    }
}
