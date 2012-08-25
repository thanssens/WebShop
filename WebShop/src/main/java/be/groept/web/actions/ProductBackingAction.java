package be.groept.web.actions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.groept.facade.BasketFacade;
import be.groept.facade.StockFacade;
import be.groept.repositories.entities.product.BasketEntity;
import be.groept.repositories.entities.product.StockEntity;

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
	private StockFacade stockFacade;

	@Autowired
	private BasketFacade basketFacade;

	private HtmlDataTable selectedProduct;

	public void addToBasket(ActionEvent ae) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String username = httpServletRequest.getUserPrincipal().getName();

		StockEntity stockEntity = (StockEntity) selectedProduct.getRowData();
		stockFacade.decreaseStock(stockEntity, 1);

		BasketEntity basketEntity = new BasketEntity();
		basketEntity.setUsername(username);
		basketEntity.setProduct(stockEntity.getProduct());
		basketEntity.setQuantity(1);

		basketFacade.addProduct(basketEntity);
	}

	public HtmlDataTable getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(HtmlDataTable selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

}