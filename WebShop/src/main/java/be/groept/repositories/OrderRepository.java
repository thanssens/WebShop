package be.groept.repositories;

import java.util.List;

import be.groept.repositories.entities.product.OrderEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface OrderRepository {

	List<OrderEntity> searchProducts(String username);

}