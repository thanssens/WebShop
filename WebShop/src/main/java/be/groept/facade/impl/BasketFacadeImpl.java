package be.groept.facade.impl;

import java.util.List;

import be.groept.facade.BasketFacade;
import be.groept.repositories.BasketRepository;
import be.groept.repositories.entities.product.BasketEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class BasketFacadeImpl implements BasketFacade {

	private final BasketRepository basketRepository;

	public BasketFacadeImpl(BasketRepository basketRepository) {
		this.basketRepository = basketRepository;
	}

	@Override
	public List<BasketEntity> getProducts(String username) {
		return basketRepository.searchProducts(username);
	}

	@Override
	public int getProductQuantity(String username, String productname) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addProduct(BasketEntity basketEntity) {
		String username = basketEntity.getUsername();
		String productName = basketEntity.getProduct().getName();
		String category = basketEntity.getProduct().getCategory();
		int price = basketEntity.getProduct().getPrice();
		int quantity = basketEntity.getQuantity();

		basketRepository.addNewProduct(username, productName, category, price, quantity);
	}

	@Override
	public void removeProduct(BasketEntity basketEntity) {
		// TODO Auto-generated method stub
		
	}

}