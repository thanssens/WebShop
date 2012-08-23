package be.groept.repositories;

import java.util.List;

import be.groept.repositories.entities.product.ProductEntity;

/**
 * 
 * @author Tom Hanssens
 * 
 */
public interface ProductRepository {

	List<ProductEntity> searchProducts();
	List<ProductEntity> searchProductsWithCriteria();

	void createEmptyCriteria();
	void setNameCriteria(String name);
	void setCategoryCriteria(String category);
	void setPriceCriteria(int price);
	void setPriceRangeCriteria(int minPrice, int maxPrice);
/*
	public enum ProductSearchCriteria {
		NAME("name", "="),
		CATEGORY("category", "="),
		MIN_PRICE("price", "<"),
		MAX_PRICE("price", ">"),
		EQL_PRICE("price", "=");
		//MIN_STOCK("stock", "<"),
		//MAX_STOCK("stock", ">"),
		//EQL_STOCK("stock", "=");

		private String name;
		private String compare;

		ProductSearchCriteria(String name, String compare) {
			this.name = name;
			this.compare = compare;
		}

		public String getQueryString(String prefix) {
			return prefix + '.' + name + compare + "?";
		}
	}

	List<ProductEntity> searchProducts(LinkedHashMap<ProductSearchCriteria, Object> criteria);
	List<ProductEntity> searchProductsFromUser(UserEntity userEntity);
*/
}