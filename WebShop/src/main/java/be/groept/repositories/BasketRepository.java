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
	List<BasketEntity> searchProduct(String username, String productname);

	void addNewProduct(String username, String productname, String category, int price, int quantity);
	void updateProductQuantity(String username, String productname, int quantity);
	void removeProduct(String username, String productname);

}