package be.groept.facade.impl;

import java.util.List;

import be.groept.facade.OrderFacade;
import be.groept.repositories.OrderRepository;
import be.groept.repositories.entities.product.OrderEntity;

public class OrderFacadeImpl implements OrderFacade {

	private final OrderRepository orderRepository;

	public OrderFacadeImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public List<OrderEntity> getOrders(String username) {
		return orderRepository.searchProducts(username);
	}

	@Override
	public void saveOrder(OrderEntity orderEntity) {
		// TODO Auto-generated method stub

	}

}
