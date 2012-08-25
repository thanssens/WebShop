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

		if (!productSearchCriteriaModel.getName().isEmpty()) {
			stockRepository.addNameCriteria(productSearchCriteriaModel.getName());
			criteria = true;
		}
		if (!productSearchCriteriaModel.getCategory().isEmpty()) {
			stockRepository.addCategoryCriteria(productSearchCriteriaModel.getCategory());
			criteria = true;
		}
		if (productSearchCriteriaModel.getMinPrice() != 0) {
			if(productSearchCriteriaModel.getMaxPrice() != 0) {
				stockRepository.addPriceRangeCriteria(productSearchCriteriaModel.getMinPrice(), productSearchCriteriaModel.getMaxPrice());
				criteria = true;
			} else {
				stockRepository.addPriceCriteria(productSearchCriteriaModel.getMinPrice());
				criteria = true;
			}
		}
		if (productSearchCriteriaModel.getMinStock() != 0) {
			if(productSearchCriteriaModel.getMaxStock() != 0) {
				stockRepository.addPriceRangeCriteria(productSearchCriteriaModel.getMinStock(), productSearchCriteriaModel.getMaxStock());
				criteria = true;
			} else {
				stockRepository.addPriceCriteria(productSearchCriteriaModel.getMinStock());
				criteria = true;
			}
		}

		if (criteria) {
			return stockRepository.searchProductsWithCriteria();
		} else {
			return stockRepository.searchProducts();
		}
	}

	@Override
	public List<StockEntity> getProduct(String productname) {
		stockRepository.addNameCriteria(productname);
		return stockRepository.searchProductsWithCriteria();
	}

	@SuppressWarnings("unused")
	@Override
	public void updateStock(String productname, int stock) {
		resetSearchCriteria();
		stockRepository.addNameCriteria(productname);
		List<StockEntity> results = stockRepository.searchProductsWithCriteria();
		for (StockEntity stockEntity : results) {
			stockRepository.updateProductStock(productname, stock);
		}
	}

	@Override
	public void resetSearchCriteria() {
		stockRepository.initCriteria();
	}

	@Override
	public int getProductStock(String productname) {
		stockRepository.addNameCriteria(productname);
		List<StockEntity> results = stockRepository.searchProductsWithCriteria();

		if (!results.isEmpty()) {
			return results.get(0).getStock();
		}

		return 0;
	}
/*
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
*/
}