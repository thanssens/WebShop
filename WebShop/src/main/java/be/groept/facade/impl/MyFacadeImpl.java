package be.groept.facade.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import be.groept.facade.MyFacade;
import be.groept.repositories.MyRepository;
import be.groept.repositories.entities.SomeEntity;

public class MyFacadeImpl implements MyFacade {

	private final MyRepository myRepository;

	public MyFacadeImpl(MyRepository myRepository) {
		this.myRepository = myRepository;
	}

	/**
	 * @see MyFacade#calculateAgeInYears(String, String)
	 */
	// NOTE THE TRANSACTIONAL ANNOTATION: each facade method should have this annotation.
	// data access can only be done within facade methods (or methods called from facade methods,
	// such as repositories etc...)
	@Transactional
	@Override
	public short calculateAgeInYears(String firstName, String lastName) {
		List<SomeEntity> result = myRepository.searchEntity(firstName, lastName);
		if (result != null && result.size() == 1) {
			SomeEntity person = result.iterator().next();
			// Calendar set to birthdate of person
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(person.getBirthDate());
			int birthYear = calendar.get(Calendar.YEAR);
			int currentYear = new GregorianCalendar().get(Calendar.YEAR);
			return (short) (currentYear - birthYear);
		}
		return -1;
	}
}
