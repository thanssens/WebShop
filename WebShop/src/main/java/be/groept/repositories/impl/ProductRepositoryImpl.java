package be.groept.repositories.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import be.groept.repositories.ProductRepository;
import be.groept.repositories.entities.product.StockEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class ProductRepositoryImpl extends HibernateTemplate implements ProductRepository {

	private Criteria criteria;

	public ProductRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		createNewStockCriteria();
	}

	@Override
	public void createNewStockCriteria() {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<StockEntity> searchProductsInStock() {
		String query = "select se from StockEntity se";
		return (List<StockEntity>) find(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockEntity> searchProductsInStockWithCriteria() {
		return (List<StockEntity>) criteria.list();
	}

	@Override
	public void addStockCriteria(int stock) {
		criteria.add(Restrictions.eq("stock", stock));
	}

	@Override
	public void addStockRangeCriteria(int minStock, int maxStock) {
		criteria.add(Restrictions.between("stock", minStock, maxStock));
	}

/*
	private ProductSearchCriteriaModel productSearchCriteriaModel;

	@Override
	public List<ProductEntity> searchProducts(ProductSearchCriteriaModel productSearchCriteriaModel) {
		Criteria criteria = getSession().createCriteria(ProductEntity.class);
		if()
		return null;
	}
/*
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductEntity> searchProducts(LinkedHashMap<ProductSearchCriteria, Object> criteria) {
		String query = "select pe from ProductEntity pe";
System.out.print(criteria.toString());
		if(criteria.isEmpty()) {
			return (List<ProductEntity>) find(query);
		}

		boolean firstElement = true;
		String parameters = "";
		for (ProductSearchCriteria criterium : criteria.keySet()) {
			if(firstElement) {
				query += " where " + criterium.getQueryString("pe");
				parameters += "," + criteria.get(criterium);
				firstElement = false;
			} else {
				query += " and " + criterium.getQueryString("pe");
				parameters += "," + criteria.get(criterium);
			}
		}
		query += parameters;

		return (List<ProductEntity>) find(query);
		//return (List<ProductEntity>) find(query, criteria.values());
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<ProductEntity> searchProducts(ProductSearchCriteria productSearchCriteria) {
//		String query = "select pe from ProductEntity pe";
//		return (List<ProductEntity>) find(query);
//		/*return (List<ProductEntity>) find("select pe from ProductEntity pe where pe.name = ? and pe.category = ? " +
//				"and pe.price >= ? and pe.price =< ? " +
//				"and pe.stock >= ? and pe.stock =< ?",
//				productSearchCriteria.getName(), productSearchCriteria.getCategory(),
//				productSearchCriteria.getMinPrice(), productSearchCriteria.getMaxPrice(),
//				productSearchCriteria.getMinStock(), productSearchCriteria.getMaxStock());*/
//	}
/*
	@Override
	public List<ProductEntity> searchProductsFromUser(UserEntity userEntity) {
		// TODO Auto-generated method stub
		return null;
	}
*/
}