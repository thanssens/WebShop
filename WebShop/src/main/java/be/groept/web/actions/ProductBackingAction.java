package be.groept.web.actions;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.groept.facade.ProductFacade;
import be.groept.repositories.entities.product.ProductEntity;
import be.groept.web.actions.model.ProductSearchCriteriaModel;

/**
 * 
 * @author Tom Hanssens
 *
 */
@ManagedBean(name = "productBackingAction")
@SessionScoped
@Controller
public class ProductBackingAction {

	@Autowired
	private ProductFacade productFacade;

	private ProductSearchCriteriaModel productSearchCriteriaModel = new ProductSearchCriteriaModel();

	private Collection<ProductEntity> products = new LinkedList<ProductEntity>();

	public String clear() {
		//productSearchCriteriaModel = new ProductSearchCriteriaModel();
		productFacade.resetSearchCriteria();
		productSearchCriteriaModel.setName("");
		productSearchCriteriaModel.setCategory("");
		productSearchCriteriaModel.setMinPrice(null);
		productSearchCriteriaModel.setMaxPrice(null);
		productSearchCriteriaModel.setMinStock(null);
		productSearchCriteriaModel.setMaxStock(null);

		return "clear";
	}

	public void search() {
		setProducts(productFacade.getProducts(productSearchCriteriaModel));
		clear();
	}

	public void toBasket() {
		//
	}
/*
	public void setProducts(Collection<ProductEntity> list) {
		this.products = (LinkedList<ProductEntity>) list;
	}
*/
	public Collection<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Collection<ProductEntity> products) {
		this.products = products;
	}

	public ProductSearchCriteriaModel getProductSearchCriteriaModel() {
		return productSearchCriteriaModel;
	}

	public void setProductSearchCriteriaModel(ProductSearchCriteriaModel productSearchCriteriaModel) {
		this.productSearchCriteriaModel = productSearchCriteriaModel;
	}

}