package be.groept.repositories;

import java.util.List;

import be.groept.repositories.entities.product.StockEntity;

/**
 * 
 * @author Tom Hanssens
 * 
 */
public interface StockRepository {

	List<StockEntity> searchProducts();
	List<StockEntity> searchProductsWithCriteria();

	void updateProductStock(String productName, int stock);

	void initCriteria();
	void addNameCriteria(String name);
	void addCategoryCriteria(String category);
	void addPriceCriteria(int price);
	void addPriceRangeCriteria(int minPrice, int maxPrice);
	void addStockCriteria(int stock);
	void addStockRangeCriteria(int minStock, int maxStock);
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