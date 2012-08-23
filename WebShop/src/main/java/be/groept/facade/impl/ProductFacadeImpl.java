package be.groept.facade.impl;

import java.util.List;

import be.groept.facade.ProductFacade;
import be.groept.repositories.ProductRepository;
import be.groept.repositories.entities.product.ProductEntity;
import be.groept.web.actions.model.ProductSearchCriteriaModel;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class ProductFacadeImpl implements ProductFacade {

	private final ProductRepository productRepository;

	public ProductFacadeImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductEntity> getProducts(ProductSearchCriteriaModel productSearchCriteriaModel) {
		boolean criteria = false;

		if(!productSearchCriteriaModel.getName().isEmpty()) {
			productRepository.setNameCriteria(productSearchCriteriaModel.getName());
			criteria = true;
		}
		if(!productSearchCriteriaModel.getCategory().isEmpty()) {
			productRepository.setCategoryCriteria(productSearchCriteriaModel.getCategory());
			criteria = true;
		}
		if(productSearchCriteriaModel.getMinPrice() != 0) {
			if(productSearchCriteriaModel.getMaxPrice() != 0) {
				productRepository.setPriceRangeCriteria(productSearchCriteriaModel.getMinPrice(), productSearchCriteriaModel.getMaxPrice());
				criteria = true;
			} else {
				productRepository.setPriceCriteria(productSearchCriteriaModel.getMinPrice());
				criteria = true;
			}
		}

		if(criteria) {
			return productRepository.searchProductsWithCriteria();
		} else {
			return productRepository.searchProducts();
		}
	}

	public void resetSearchCriteria() {
		productRepository.createEmptyCriteria();
	}
/*
	@Override
	public List<ProductEntity> getProducts(
			ProductSearchCriteriaModel productSearchCriteriaModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashMap<ProductSearchCriteria, Object> buildSearchCriteria(ProductSearchCriteriaModel productSearchCriteriaModel) {
		LinkedHashMap<ProductSearchCriteria, Object> criteria = new LinkedHashMap<ProductSearchCriteria, Object>();

		if(productSearchCriteriaModel.getName() != null || productSearchCriteriaModel.getName().isEmpty() || productSearchCriteriaModel.getName().length() != 0) {
			criteria.put(ProductSearchCriteria.NAME, productSearchCriteriaModel.getName());
		}
		if(productSearchCriteriaModel.getCategory() != null || productSearchCriteriaModel.getCategory().isEmpty()) {
			criteria.put(ProductSearchCriteria.CATEGORY, productSearchCriteriaModel.getCategory());
		}
		if(productSearchCriteriaModel.getMinPrice() != null || productSearchCriteriaModel.getMinPrice() != 0) {
			if(productSearchCriteriaModel.getMaxPrice() != null || productSearchCriteriaModel.getMaxPrice() != 0) {
				criteria.put(ProductSearchCriteria.MIN_PRICE, productSearchCriteriaModel.getMinPrice());
				criteria.put(ProductSearchCriteria.MAX_PRICE, productSearchCriteriaModel.getMaxPrice());
			} else {
				criteria.put(ProductSearchCriteria.EQL_PRICE, productSearchCriteriaModel.getMinPrice());
			}
		}/*
		if(productSearchCriteriaModel.getMinStock() != null) {
			if(productSearchCriteriaModel.getMaxStock() != null) {
				criteria.put(ProductSearchCriteria.MIN_STOCK, productSearchCriteriaModel.getMinStock());
				criteria.put(ProductSearchCriteria.MAX_STOCK, productSearchCriteriaModel.getMaxStock());
			} else {
				criteria.put(ProductSearchCriteria.EQL_STOCK, productSearchCriteriaModel.getMinStock());
			}
		}

		return criteria;
	}

	@Override
	public List<ProductEntity> getProducts(ProductSearchCriteriaModel productSearchCriteriaModel) {
		return productRepository.searchProducts(buildSearchCriteria(productSearchCriteriaModel));
	}
*/
}