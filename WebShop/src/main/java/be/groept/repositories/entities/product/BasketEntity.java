package be.groept.repositories.entities.product;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class BasketEntity {

	private Long id;

	private ProductEntity productEntity;
	private Integer amount;

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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}