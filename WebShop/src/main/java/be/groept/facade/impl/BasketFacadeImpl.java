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
	public int getTotalProducts(String username) {
		int totalProducts = 0;

		for (BasketEntity basketEntity : getProducts(username)) {
			totalProducts += basketEntity.getQuantity();
		}

		return totalProducts;
	}

	@Override
	public void addProduct(BasketEntity basketEntity) {
		String username = basketEntity.getUsername();
		String productname = basketEntity.getProduct().getName();
		String category = basketEntity.getProduct().getCategory();
		int price = basketEntity.getProduct().getPrice();
		int quantity = basketEntity.getQuantity();

		List<BasketEntity> results = basketRepository.searchProductByName(username, productname);
		if (results.isEmpty()) {
			basketRepository.addNewProduct(username, productname, category, price, quantity);
		} else {
			//quantity++;
			basketRepository.updateProductQuantity(username, productname, quantity);
		}
	}

	@Override
	public void removeProduct(BasketEntity basketEntity) {
		// TODO Auto-generated method stub
		
	}

}