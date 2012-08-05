package be.groept.facade;

import be.groept.repositories.MyRepository;
import be.groept.repositories.entities.SomeEntity;

public interface MyFacade {

	/**
	 * Searches a person in the database with the given first and last name, using
	 * {@link MyRepository}. If exactly one match is found, calculates the age of the person in
	 * years based upon its {@link SomeEntity#getBirthDate()} If no person was found, or more then
	 * one matching first/lastname, -1 is returned
	 */
	short calculateAgeInYears(String firstName, String lastName);
}
