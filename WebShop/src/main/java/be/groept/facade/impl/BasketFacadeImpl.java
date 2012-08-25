package be.groept.facade.impl;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import be.groept.facade.BasketFacade;
import be.groept.repositories.BasketRepository;
import be.groept.repositories.entities.product.BasketEntity;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class BasketFacadeImpl implements BasketFacade {

	private final BasketRepository basketRepository;

	public BasketFacadeImpl(BasketRepository basketRepository) {
		this.basketRepository = basketRepository;
	}

	@Override
	public List<BasketEntity> getProducts() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String username = httpServletRequest.getUserPrincipal().getName();
System.out.println(username);
		return basketRepository.searchProducts(username);
	}

}