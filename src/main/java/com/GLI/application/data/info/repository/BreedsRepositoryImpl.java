package com.GLI.application.data.info.repository;

import com.GLI.application.data.info.datasource.BreedsDataSource;
import com.GLI.application.data.info.entity.Breed;
import com.GLI.application.data.info.entity.BreedsEntity;
import com.GLI.application.data.info.mappper.BreedsMapper;
import com.GLI.application.domain.model.BreedsModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BreedsRepositoryImpl implements BreedsRepository {

    private final BreedsMapper breadsModelBreadsEntityMapper;
    private final BreedsDataSource breedsDataSource;
    protected final Connection connection;

    public BreedsRepositoryImpl(
            BreedsMapper breadsModelBreadsEntityMapper,
            BreedsDataSource breedsDataSource, DataSource dataSource
    ) throws SQLException {
        this.breadsModelBreadsEntityMapper = breadsModelBreadsEntityMapper;
        this.breedsDataSource = breedsDataSource;
        this.connection = dataSource.getConnection();
    }

    @Override
    public List<BreedsModel> getAllBreeds() {
        BreedsEntity breedsEntity = this.breedsDataSource.getAllBreeds();
        return this.breadsModelBreadsEntityMapper.reverseMap(breedsEntity);
    }

    @Override
    public Breed create(String breed) throws SQLException {
        var insertStatement = this.connection.prepareStatement("INSERT INTO breeds (breed) VALUES ('" + breed + "')", new String[]{"id"});
        var affected = insertStatement.executeUpdate();
        if (affected == 0) {
            throw new SQLException("Creating breed failed, no rows affected.");
        }
        var generatedKeys = insertStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return new Breed(generatedKeys.getInt(1), breed);
        } else {
            generatedKeys.close();
            throw new SQLException("Creating breed failed, no ID obtained.");
        }
    }

    @Override
    public Breed update(String breed) throws SQLException {
        var updateStatement = this.connection.prepareStatement("UPDATE breeds SET breed = '" + breed + "' WHERE breed = '" + breed + "'");
        var affected = updateStatement.executeUpdate();
        if (affected == 0) {
            throw new SQLException("Updating breed failed, no rows affected.");
        }
        var generatedKeys = updateStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return new Breed(generatedKeys.getInt(1), breed);
        } else {
            generatedKeys.close();
            throw new SQLException("Updating breed failed, no ID obtained.");
        }
    }

    @Override
    public Breed delete(String breed) throws SQLException {
        var deleteStatement = this.connection.prepareStatement("DELETE FROM breeds WHERE breed = '" + breed + "'");
        var affected = deleteStatement.executeUpdate();
        if (affected == 0) {
            throw new SQLException("Deleting breed failed, no rows affected.");
        }
        var generatedKeys = deleteStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return new Breed(generatedKeys.getInt(1), breed);
        } else {
            generatedKeys.close();
            throw new SQLException("Deleting breed failed, no ID obtained.");
        }
    }

    @Override
    public Breed get(String breed) throws SQLException {
        var selectStatement = this.connection.prepareStatement("SELECT * FROM breeds WHERE breed = '" + breed + "'");
        var resultSet = selectStatement.executeQuery();
        if (resultSet.next()) {
            return new Breed(resultSet.getInt("id"), resultSet.getString("breed"));
        } else {
            throw new SQLException("Getting breed failed, no rows affected.");
        }
    }

    @Override
    public Breed get(int id) throws SQLException {
        var selectStatement = this.connection.prepareStatement("SELECT * FROM breeds WHERE id = '" + id + "'");
        var resultSet = selectStatement.executeQuery();
        if (resultSet.next()) {
            return new Breed(resultSet.getInt("id"), resultSet.getString("breed"));
        } else {
            throw new SQLException("Getting breed failed, no rows affected.");
        }
    }

    @Override
    public Breed get(Breed breed) throws SQLException {
        var selectStatement = this.connection.prepareStatement("SELECT * FROM breeds WHERE breed = '" + breed.getBreed() + "'");
        var resultSet = selectStatement.executeQuery();
        if (resultSet.next()) {
            return new Breed(resultSet.getInt("id"), resultSet.getString("breed"));
        } else {
            resultSet.close();
            throw new SQLException("Getting breed failed, no rows affected.");
        }
    }

    @Override
    public List<Breed> getAll() throws SQLException {
        var selectStatement = this.connection.prepareStatement("SELECT * FROM breeds");
        var resultSet = selectStatement.executeQuery();
        List<Breed> breeds = new ArrayList<>();
        while (resultSet.next()) {
            breeds.add(new Breed(resultSet.getInt("id"), resultSet.getString("breed")));
        }
        return breeds;
    }
}
