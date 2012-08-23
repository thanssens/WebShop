package be.groept.web.actions;

import java.util.Collection;
import java.util.LinkedList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;

import be.groept.repositories.entities.product.ProductEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
@ManagedBean(name = "basketBackingAction")
@SessionScoped
@Controller
public class BasketBackingAction {

	private Collection<ProductEntity> products = new LinkedList<ProductEntity>();
	private int quantity;

	public void order() {
		//
	}

	public void clear() {
		//
	}

	public Collection<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Collection<ProductEntity> products) {
		this.products = products;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}