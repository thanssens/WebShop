package be.groept.web.actions;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.groept.repositories.OrderRepository;
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

	@Autowired
	OrderRepository orderRepository;

	private List<OrderEntity> orders = new LinkedList<OrderEntity>();

	public String getUsername() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return httpServletRequest.getUserPrincipal().getName();
	}

	public List<OrderEntity> getOrders() {
		setOrders(orderRepository.searchProducts(getUsername()));
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

}