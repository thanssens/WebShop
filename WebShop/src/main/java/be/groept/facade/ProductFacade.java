package be.groept.facade;

import java.util.List;

import be.groept.repositories.entities.product.ProductEntity;
import be.groept.web.actions.model.ProductSearchCriteriaModel;

/**
 * 
 * @author Tom Hanssens
 *
 */
public interface ProductFacade {

	List<ProductEntity> getProducts(ProductSearchCriteriaModel productSearchCriteriaModel);

	void resetSearchCriteria();

	//LinkedHashMap<ProductSearchCriteria, Object> buildSearchCriteria(ProductSearchCriteriaModel productSearchCriteriaModel);

}