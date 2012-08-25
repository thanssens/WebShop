package be.groept.web.actions;

import java.util.List;

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

	public HtmlDataTable getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(HtmlDataTable selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public String getUsername() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return httpServletRequest.getUserPrincipal().getName();
	}

	public void addToBasket(ActionEvent ae) {
		StockEntity stockEntity = (StockEntity) selectedProduct.getRowData();
		String productname = stockEntity.getProduct().getName();
		int stock = stockEntity.getStock() - 1;

		stockEntity.setStock(stock);
		stockFacade.updateStock(productname, stock);

		BasketEntity basketEntity = new BasketEntity();
		basketEntity.setUsername(getUsername());
		basketEntity.setProduct(stockEntity.getProduct());
		basketEntity.setQuantity(1);

		basketFacade.addProduct(basketEntity);
	}

	public void removeFromBasket() {
		for (BasketEntity basketEntity : basketFacade.getProducts(getUsername())) {
			basketFacade.removeProduct(basketEntity);

			String productname = basketEntity.getProduct().getName();
			List<StockEntity> results = stockFacade.getProduct(productname);

			for (StockEntity stockEntity : results) {
				int stock = stockFacade.getProductStock(productname) + basketEntity.getQuantity();
				stockEntity.setStock(stock);
				stockFacade.updateStock(productname, stock);
			}
		}
	}

}