package be.groept.repositories.entities.product;

import java.util.Collection;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class OrderEntity {

	private Long id;

	private String username;

	private Collection<BasketEntity> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<BasketEntity> getProducts() {
		return products;
	}

	public void setProducts(Collection<BasketEntity> products) {
		this.products = products;
	}

}