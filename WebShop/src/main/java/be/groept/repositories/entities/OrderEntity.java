package be.groept.repositories.entities;

import java.util.Collection;

import be.groept.repositories.entities.product.ProductEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class OrderEntity {

	private Long id;

	private Collection<ProductEntity> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Collection<ProductEntity> products) {
		this.products = products;
	}

}