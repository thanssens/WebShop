package be.groept.facade;

import java.util.List;

import be.groept.repositories.entities.product.BasketEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface BasketFacade {

	List<BasketEntity> getProducts(String username);

	int getProductQuantity(String username, String productname);

	void addProduct(BasketEntity basketEntity);
	void removeProduct(BasketEntity basketEntity);

}