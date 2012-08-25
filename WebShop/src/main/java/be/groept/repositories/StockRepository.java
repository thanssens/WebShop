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

	void updateProductStock(String productname, int stock);

	void initCriteria();
	void addNameCriteria(String name);
	void addCategoryCriteria(String category);
	void addPriceCriteria(int price);
	void addPriceRangeCriteria(int minPrice, int maxPrice);
	void addStockCriteria(int stock);
	void addStockRangeCriteria(int minStock, int maxStock);

}