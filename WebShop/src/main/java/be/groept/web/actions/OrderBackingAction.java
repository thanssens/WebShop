package be.groept.web.actions;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;

import be.groept.repositories.entities.product.OrderEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
@ManagedBean(name = "orderBackingAction")
@SessionScoped
@Controller
public class OrderBackingAction {

	private List<OrderEntity> orders = new LinkedList<OrderEntity>();

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

}