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

}