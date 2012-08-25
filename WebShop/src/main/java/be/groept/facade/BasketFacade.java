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

	int getTotalProducts(String username);

	void addProduct(BasketEntity basketEntity);
	void removeProduct(BasketEntity basketEntity);

}