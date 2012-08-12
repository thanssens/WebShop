package be.groept.repositories.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import be.groept.repositories.MyRepository;
import be.groept.repositories.entities.SomeEntity;

public class MyRepositoryImpl extends HibernateTemplate implements MyRepository {

	public MyRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SomeEntity> searchEntity(String firstName, String lastName) {
		return (List<SomeEntity>) find("select se from SomeEntity se where se.firstName = ? and se.lastName = ?",
				firstName, lastName);
	}
}