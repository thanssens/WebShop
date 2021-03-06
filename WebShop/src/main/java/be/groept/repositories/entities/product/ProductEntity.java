package be.groept.repositories.entities.product;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class ProductEntity {

	private Long id;

	private String name;
	private String category;
	private Integer price;

	public void setCategoryName(ProductCategoryEntity productCategoryEntity) {
		category = productCategoryEntity.getName().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}