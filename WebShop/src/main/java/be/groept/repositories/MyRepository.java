package be.groept.repositories;

import java.util.List;

import be.groept.repositories.entities.SomeEntity;

public interface MyRepository {

	List<SomeEntity> searchEntity(String firstName, String lastName);

}