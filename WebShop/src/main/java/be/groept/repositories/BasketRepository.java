package be.groept.repositories;

import java.util.List;

import be.groept.repositories.entities.product.BasketEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface BasketRepository {

	List<BasketEntity> searchProducts(String username);

	void addNewProduct(String username, String productName, String category, int price, int quantity);
	void updateProductQuantity(String username, String productName, int quantity);
	void removeProduct(String username, String productName);

}