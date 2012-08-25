package be.groept.repositories.entities.product;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class BasketEntity {

	private Long id;

	private String username;
	private ProductEntity product;
	private Integer quantity;
	//private Map<ProductEntity, Integer> products;

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

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
/*
	public Map<ProductEntity, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<ProductEntity, Integer> products) {
		this.products = products;
	}
*/
}