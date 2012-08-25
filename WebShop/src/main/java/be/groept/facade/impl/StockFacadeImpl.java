package be.groept.facade.impl;

import java.util.List;

import be.groept.facade.StockFacade;
import be.groept.repositories.StockRepository;
import be.groept.repositories.entities.product.StockEntity;
import be.groept.web.actions.model.ProductSearchCriteriaModel;

/**
 * 
 * @author Tom Hanssens
 *
 */
public class StockFacadeImpl implements StockFacade {

	private final StockRepository stockRepository;

	public StockFacadeImpl(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public List<StockEntity> getProducts(ProductSearchCriteriaModel productSearchCriteriaModel) {
		boolean criteria = false;

		if(!productSearchCriteriaModel.getName().isEmpty()) {
			stockRepository.addNameCriteria(productSearchCriteriaModel.getName());
			criteria = true;
		}
		if(!productSearchCriteriaModel.getCategory().isEmpty()) {
			stockRepository.addCategoryCriteria(productSearchCriteriaModel.getCategory());
			criteria = true;
		}
		if(productSearchCriteriaModel.getMinPrice() != 0) {
			if(productSearchCriteriaModel.getMaxPrice() != 0) {
				stockRepository.addPriceRangeCriteria(productSearchCriteriaModel.getMinPrice(), productSearchCriteriaModel.getMaxPrice());
				criteria = true;
			} else {
				stockRepository.addPriceCriteria(productSearchCriteriaModel.getMinPrice());
				criteria = true;
			}
		}

		if(criteria) {
			return stockRepository.searchProductsWithCriteria();
		} else {
			return stockRepository.searchProducts();
		}
	}

	@Override
	public void resetSearchCriteria() {
		stockRepository.initCriteria();
	}

	@Override
	public void increaseStock(StockEntity stockEntity, int stock) {
		String productName = stockEntity.getProduct().getName();
		int newStock = stockEntity.getStock() + stock;

		stockEntity.setStock(newStock);
		stockRepository.updateProductStock(productName, newStock);
	}

	@Override
	public void decreaseStock(StockEntity stockEntity, int stock) {
		String productName = stockEntity.getProduct().getName();
		int newStock = stockEntity.getStock() - stock;

		stockEntity.setStock(newStock);
		stockRepository.updateProductStock(productName, newStock);
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