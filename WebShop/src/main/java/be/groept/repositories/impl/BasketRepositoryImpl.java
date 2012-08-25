package be.groept.repositories.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<BasketEntity> searchProductByName(String username, String productname) {
		return (List<BasketEntity>) find("select be from BasketEntity be where be.username = ? and be.product.name = ?", username, productname);
	}

	@Override
	public void addNewProduct(String username, String productname, String category, int price, int quantity) {
		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		String query = "INSERT INTO baskets (username, name, category, price, quantity) VALUES ('" + username + "','" + productname + "','" + category + "',"  + price + "," + quantity + ")";
		SQLQuery sqlQuery = session.createSQLQuery(query);
		sqlQuery.executeUpdate();

		transaction.commit();
		session.close();
	}

	@Override
	public void updateProductQuantity(String username, String productName, int quantity) {
		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		String query = "update BasketEntity be set be.quantity = :quantity where be.username = :username and be.product.name = :name and be.quantity >= 0";
		@SuppressWarnings("unused")
		int updatedProducts = session.createQuery(query)
				.setInteger("quantity", quantity)
				.setString("username", username)
				.setString("name", productName)
				.executeUpdate();

		transaction.commit();
		session.close();
	}

	@Override
	public void removeProduct(String username, String productName) {
		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		String query = "delete BasketEntity be where be.username = :username and be.product.name = :name";
		@SuppressWarnings("unused")
		int updatedProducts = session.createQuery(query)
				.setString("username", username)
				.setString("name", productName)
				.executeUpdate();

		transaction.commit();
		session.close();
	}

}