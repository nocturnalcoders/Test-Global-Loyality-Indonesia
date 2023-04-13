package com.GLI.application.data.info.repository;

import com.GLI.application.data.info.datasource.SubBreedsDataSource;
import com.GLI.application.data.info.entity.SubBreed;
import com.GLI.application.data.info.entity.SubBreedsEntity;
import com.GLI.application.domain.model.SubBreedsModel;
import com.GLI.common.Mapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SubBreedsRepositoryImpl implements SubBreedsRepository {

    private final Mapper<SubBreedsModel, SubBreedsEntity> subBreedsModelSubBreedsEntityMapper;
    private final SubBreedsDataSource subBreedsDataSource;
    protected final Connection connection;

    public SubBreedsRepositoryImpl(
            Mapper<SubBreedsModel, SubBreedsEntity> subBreedsModelSubBreedsEntityMapper,
            SubBreedsDataSource subBreedsDataSource, DataSource dataSource
    ) throws SQLException {
        this.subBreedsModelSubBreedsEntityMapper = subBreedsModelSubBreedsEntityMapper;
        this.subBreedsDataSource = subBreedsDataSource;
        this.connection = dataSource.getConnection();
    }

    @Override
    public SubBreedsModel getSubBreeds(String breed) {
        SubBreedsEntity subBreedsEntity = this.subBreedsDataSource.getSubBreeds(breed);
        return this.subBreedsModelSubBreedsEntityMapper.reverseMap(subBreedsEntity);
    }

    @Override
    public SubBreed create(int breedId, String subBreed) throws SQLException {
        var insertStatement = this.connection.prepareStatement("INSERT INTO sub_breeds (BREED_ID, SUB_BREED) VALUES ('" + breedId + "', '" + subBreed + "')", new String[]{"id"});
        var affected = insertStatement.executeUpdate();
        if (affected == 0) {
            throw new SQLException("Creating sub breed failed, no rows affected.");
        }

        var generatedKeys = insertStatement.getGeneratedKeys();

        if (generatedKeys.next()) {
            return new SubBreed(generatedKeys.getInt(1), breedId, subBreed);
        } else {
            generatedKeys.close();
            throw new SQLException("Creating sub breed failed, no ID obtained.");
        }
    }

    @Override
    public SubBreed update(String subBreed) throws SQLException {
        var updateStatement = this.connection.prepareStatement("UPDATE sub_breeds SET sub_breed = '" + subBreed + "' WHERE sub_breed = '" + subBreed + "'", new String[]{"id", "breed_id"});
        var affected = updateStatement.executeUpdate();
        if (affected == 0) {
            throw new SQLException("Updating sub breed failed, no rows affected.");
        }
        var generatedKeys = updateStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return new SubBreed(generatedKeys.getInt(1), generatedKeys.getInt(2), subBreed);
        } else {
            generatedKeys.close();
            throw new SQLException("Updating sub breed failed, no ID obtained.");
        }
    }

    @Override
    public SubBreed update(int breedId, String subBreed) throws SQLException {
        return null;
    }

    @Override
    public SubBreed delete(String subBreed) throws SQLException {
        return null;
    }

    @Override
    public SubBreed delete(int breedId, String subBreed) throws SQLException {
        return null;
    }
}
