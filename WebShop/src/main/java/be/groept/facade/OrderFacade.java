package be.groept.facade;

import java.util.List;

import be.groept.repositories.entities.product.OrderEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface OrderFacade {

	List<OrderEntity> getOrders(String username);

	void saveOrder(OrderEntity orderEntity);

}