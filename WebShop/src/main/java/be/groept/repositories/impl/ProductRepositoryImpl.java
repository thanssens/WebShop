package be.groept.repositories.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import be.groept.repositories.ProductRepository;
import be.groept.repositories.entities.product.ProductEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class ProductRepositoryImpl extends HibernateTemplate implements ProductRepository {

	private Criteria criteria;

	public ProductRepositoryImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		createEmptyCriteria();
	}

	@Override
	public void createEmptyCriteria() {
		criteria = getSessionFactory().openStatelessSession().createCriteria(ProductEntity.class);
	}

	@Override
	public void setNameCriteria(String name) {
		criteria.add(Restrictions.like("name", name));
	}

	@Override
	public void setCategoryCriteria(String category) {
		criteria.add(Restrictions.like("category", category));
	}

	@Override
	public void setPriceCriteria(int price) {
		criteria.add(Restrictions.eq("price", price));
	}

	@Override
	public void setPriceRangeCriteria(int minPrice, int maxPrice) {
		criteria.add(Restrictions.between("price", minPrice, maxPrice));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductEntity> searchProducts() {
		String query = "select pe from ProductEntity pe";
		return (List<ProductEntity>) find(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductEntity> searchProductsWithCriteria() {
		return (List<ProductEntity>) criteria.list();
	}

/*
	private ProductSearchCriteriaModel productSearchCriteriaModel;

	@Override
	public List<ProductEntity> searchProducts(ProductSearchCriteriaModel productSearchCriteriaModel) {
		Criteria criteria = getSession().createCriteria(ProductEntity.class);
		if()
		return null;
	}

	@Override
	public String buildNameQuery(String name) {
		String query = "";
		return query;
	}

	@Override
	public String buildCategoryQuery(String category) {
		String query = "";
		return query;
	}

	@Override
	public String buildPriceQuery(int price) {
		String query = "";
		return query;
	}

	@Override
	public String buildPriceQuery(int minPrice, int maxPrice) {
		String query = "";
		return query;
	}

	public ProductSearchCriteriaModel getProductSearchCriteriaModel() {
		return productSearchCriteriaModel;
	}

	public void setProductSearchCriteriaModel(ProductSearchCriteriaModel productSearchCriteriaModel) {
		this.productSearchCriteriaModel = productSearchCriteriaModel;
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