package be.groept.web.actions;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.groept.facade.BasketFacade;
import be.groept.repositories.entities.product.BasketEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
@ManagedBean(name = "basketBackingAction")
@SessionScoped
@Controller
public class BasketBackingAction {

	@Autowired
	private BasketFacade basketFacade;

	private List<BasketEntity> products = new LinkedList<BasketEntity>();

	private int totalProducts;

	boolean sortAscending = true;

	public void clear() {
		//
	}

	public void order() {
		//create new order from products
	}

	public String getUsername() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return httpServletRequest.getUserPrincipal().getName();
	}

	public List<BasketEntity> getProducts() {
		try {
			setProducts(basketFacade.getProducts(getUsername()));
			return products;
		} catch (Exception e) {
			return new LinkedList<BasketEntity>();
		}
	}

	public void setProducts(List<BasketEntity> products) {
		this.products = products;
	}

	public int getTotalProducts() {
		setTotalProducts(basketFacade.getTotalQuantity(getUsername()));
		return totalProducts;
	}

	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}

	public String sortByName() {
		if (sortAscending) {
			Collections.sort(products, new Comparator<BasketEntity>() {

				@Override
				public int compare(BasketEntity product1, BasketEntity product2) {
					return product1.getProduct().getName().compareTo(product2.getProduct().getName());
				}

			});

			sortAscending = false;
		} else {
			Collections.sort(products, new Comparator<BasketEntity>() {

				@Override
				public int compare(BasketEntity product1, BasketEntity product2) {
					return product2.getProduct().getName().compareTo(product1.getProduct().getName());
				}

			});

			sortAscending = true;
		}

		return null;
	}

	public String sortByCategory() {
		if (sortAscending) {
			Collections.sort(products, new Comparator<BasketEntity>() {

				@Override
				public int compare(BasketEntity product1, BasketEntity product2) {
					return product1.getProduct().getCategory().compareTo(product2.getProduct().getCategory());
				}

			});

			sortAscending = false;
		} else {
			Collections.sort(products, new Comparator<BasketEntity>() {

				@Override
				public int compare(BasketEntity product1, BasketEntity product2) {
					return product2.getProduct().getCategory().compareTo(product1.getProduct().getCategory());
				}

			});

			sortAscending = true;
		}

		return null;
	}

	public String sortByPrice() {
		if (sortAscending) {
			Collections.sort(products, new Comparator<BasketEntity>() {

				@Override
				public int compare(BasketEntity product1, BasketEntity product2) {
					return product1.getProduct().getPrice().compareTo(product2.getProduct().getPrice());
				}

			});

			sortAscending = false;
		} else {
			Collections.sort(products, new Comparator<BasketEntity>() {

				@Override
				public int compare(BasketEntity product1, BasketEntity product2) {
					return product2.getProduct().getPrice().compareTo(product1.getProduct().getPrice());
				}

			});

			sortAscending = true;
		}

		return null;
	}

	public String sortByQuantity() {
		if (sortAscending) {
			Collections.sort(products, new Comparator<BasketEntity>() {

				@Override
				public int compare(BasketEntity product1, BasketEntity product2) {
					return product1.getQuantity().compareTo(product2.getQuantity());
				}

			});

			sortAscending = false;
		} else {
			Collections.sort(products, new Comparator<BasketEntity>() {

				@Override
				public int compare(BasketEntity product1, BasketEntity product2) {
					return product2.getQuantity().compareTo(product1.getQuantity());
				}

			});

			sortAscending = true;
		}

		return null;
	}

}