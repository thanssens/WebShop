package be.groept.facade;

import java.util.List;

import be.groept.repositories.entities.product.StockEntity;
import be.groept.web.actions.model.ProductSearchCriteriaModel;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface StockFacade {

	List<StockEntity> getProducts(ProductSearchCriteriaModel productSearchCriteriaModel);
	List<StockEntity> getProduct(String productname);

	void updateStock(String productname, int stock);
	void resetSearchCriteria();

	int getProductStock(String productname);

}