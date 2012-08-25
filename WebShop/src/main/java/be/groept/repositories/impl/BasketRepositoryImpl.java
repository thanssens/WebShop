package be.groept.repositories.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import be.groept.repositories.BasketRepository;
import be.groept.repositories.entities.product.BasketEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class BasketRepositoryImpl extends HibernateTemplate implements BasketRepository {

	public BasketRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BasketEntity> searchProducts(String username) {
		return (List<BasketEntity>) find("select be from BasketEntity be where be.username = ?", username);
	}

}