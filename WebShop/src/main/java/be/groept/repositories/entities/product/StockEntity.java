package be.groept.repositories.entities.product;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class StockEntity {

	private Long id;

	private ProductEntity product;
	private Integer stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}