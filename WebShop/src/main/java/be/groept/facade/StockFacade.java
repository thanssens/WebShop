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

	void resetSearchCriteria();

	void increaseStock(StockEntity stockEntity, int stock);
	void decreaseStock(StockEntity stockEntity, int stock);

}