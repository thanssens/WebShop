package be.groept.repositories.entities.product;

import java.util.Map;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class BasketEntity {

	private Long id;

	private Map<ProductEntity, Integer> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<ProductEntity, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<ProductEntity, Integer> products) {
		this.products = products;
	}

}