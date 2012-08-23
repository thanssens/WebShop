package be.groept.repositories.entities.product;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class StockEntity {

	private Long id;

	private ProductEntity productEntity;
	private Integer stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}