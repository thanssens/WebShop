package be.groept.web.actions;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.groept.facade.StockFacade;
import be.groept.repositories.entities.product.StockEntity;
import be.groept.web.actions.model.ProductSearchCriteriaModel;

/**
 * 
 * @author Tom Hanssens
 *
 */
@ManagedBean(name = "stockBackingAction")
@SessionScoped
@Controller
public class StockBackingAction {

	@Autowired
	private StockFacade stockFacade;

	private List<StockEntity> products = new LinkedList<StockEntity>();

	private ProductSearchCriteriaModel productSearchCriteriaModel = new ProductSearchCriteriaModel();

	boolean sortAscending = true;

	public String clear() {
		stockFacade.resetSearchCriteria();
		productSearchCriteriaModel.setName("");
		productSearchCriteriaModel.setCategory("");
		productSearchCriteriaModel.setMinPrice(null);
		productSearchCriteriaModel.setMaxPrice(null);
		productSearchCriteriaModel.setMinStock(null);
		productSearchCriteriaModel.setMaxStock(null);

		return "clear";
	}

	public void search() {
		products = stockFacade.getProducts(productSearchCriteriaModel);
		clear();
	}

	public String sortByName() {
		if (sortAscending) {
			Collections.sort(products, new Comparator<StockEntity>() {

				@Override
				public int compare(StockEntity product1, StockEntity product2) {
					return product1.getProduct().getName().compareTo(product2.getProduct().getName());
				}

			});

			sortAscending = false;
		} else {
			Collections.sort(products, new Comparator<StockEntity>() {

				@Override
				public int compare(StockEntity product1, StockEntity product2) {
					return product2.getProduct().getName().compareTo(product1.getProduct().getName());
				}

			});

			sortAscending = true;
		}

		return null;
	}

	public String sortByCategory() {
		if (sortAscending) {
			Collections.sort(products, new Comparator<StockEntity>() {

				@Override
				public int compare(StockEntity product1, StockEntity product2) {
					return product1.getProduct().getCategory().compareTo(product2.getProduct().getCategory());
				}

			});

			sortAscending = false;
		} else {
			Collections.sort(products, new Comparator<StockEntity>() {

				@Override
				public int compare(StockEntity product1, StockEntity product2) {
					return product2.getProduct().getCategory().compareTo(product1.getProduct().getCategory());
				}

			});

			sortAscending = true;
		}

		return null;
	}

	public String sortByPrice() {
		if (sortAscending) {
			Collections.sort(products, new Comparator<StockEntity>() {

				@Override
				public int compare(StockEntity product1, StockEntity product2) {
					return product1.getProduct().getPrice().compareTo(product2.getProduct().getPrice());
				}

			});

			sortAscending = false;
		} else {
			Collections.sort(products, new Comparator<StockEntity>() {

				@Override
				public int compare(StockEntity product1, StockEntity product2) {
					return product2.getProduct().getPrice().compareTo(product1.getProduct().getPrice());
				}

			});

			sortAscending = true;
		}

		return null;
	}

	public String sortByStock() {
		if (sortAscending) {
			Collections.sort(products, new Comparator<StockEntity>() {

				@Override
				public int compare(StockEntity product1, StockEntity product2) {
					return product1.getStock().compareTo(product2.getStock());
				}

			});

			sortAscending = false;
		} else {
			Collections.sort(products, new Comparator<StockEntity>() {

				@Override
				public int compare(StockEntity product1, StockEntity product2) {
					return product2.getStock().compareTo(product1.getStock());
				}

			});

			sortAscending = true;
		}

		return null;
	}
/*
	public void setProducts(List<ProductEntity> list) {
		this.products = (LinkedList<ProductEntity>) list;
	}
*/
	public List<StockEntity> getProducts() {
		return products;
	}

	public void setProducts(List<StockEntity> products) {
		this.products = products;
	}

	public ProductSearchCriteriaModel getProductSearchCriteriaModel() {
		return productSearchCriteriaModel;
	}

	public void setProductSearchCriteriaModel(ProductSearchCriteriaModel productSearchCriteriaModel) {
		this.productSearchCriteriaModel = productSearchCriteriaModel;
	}

}