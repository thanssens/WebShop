package be.groept.repositories.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import be.groept.repositories.StockRepository;
import be.groept.repositories.entities.product.StockEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class StockRepositoryImpl extends HibernateTemplate implements StockRepository {

	private Criteria criteria;

	public StockRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		initCriteria();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockEntity> searchProducts() {
		return (List<StockEntity>) find("select se from StockEntity se");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockEntity> searchProductsWithCriteria() {
		return (List<StockEntity>) criteria.list();
	}

	@Override
	public void updateProductStock(String productname, int stock) {
		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		String query = "update StockEntity se set se.stock = :stock where se.product.name = :name and se.stock >= 0";
		@SuppressWarnings("unused")
		int updatedProducts = session.createQuery(query)
				.setInteger("stock", stock)
				.setString("name", productname)
				.executeUpdate();

		transaction.commit();
		session.close();
	}

	@Override
	public void initCriteria() {
		criteria = getSessionFactory().openStatelessSession().createCriteria(StockEntity.class);
	}

	@Override
	public void addNameCriteria(String name) {
		criteria.add(Restrictions.like("product.name", name));
	}

	@Override
	public void addCategoryCriteria(String category) {
		criteria.add(Restrictions.like("product.category", category));
	}

	@Override
	public void addPriceCriteria(int price) {
		criteria.add(Restrictions.eq("product.price", price));
	}

	@Override
	public void addPriceRangeCriteria(int minPrice, int maxPrice) {
		criteria.add(Restrictions.between("product.price", minPrice, maxPrice));
	}

	@Override
	public void addStockCriteria(int stock) {
		criteria.add(Restrictions.eq("stock", stock));
	}

	@Override
	public void addStockRangeCriteria(int minStock, int maxStock) {
		criteria.add(Restrictions.between("stock", minStock, maxStock));
	}

}