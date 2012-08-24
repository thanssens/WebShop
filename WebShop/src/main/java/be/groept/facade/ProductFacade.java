package be.groept.facade;

import java.util.List;

import be.groept.repositories.entities.product.StockEntity;
import be.groept.web.actions.model.ProductSearchCriteriaModel;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface ProductFacade {

	List<StockEntity> getProductsInStock(ProductSearchCriteriaModel productSearchCriteriaModel);

	void resetSearchCriteria();

	//LinkedHashMap<ProductSearchCriteria, Object> buildSearchCriteria(ProductSearchCriteriaModel productSearchCriteriaModel);

}