package be.groept.repositories.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import be.groept.repositories.OrderRepository;
import be.groept.repositories.entities.product.OrderEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class OrderRepositoryImpl extends HibernateTemplate implements OrderRepository {

	public OrderRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderEntity> searchProducts(String username) {
		return (List<OrderEntity>) find("select oe from OrderEntity oe where oe.username = ?", username);
	}

}