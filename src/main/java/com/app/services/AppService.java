package com.app.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.dao.CategoryDao;
import com.app.dao.ProductDao;
import com.app.dto.RequestDto;
import com.app.models.Category;
import com.app.models.Product;

@Service
public class AppService {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional(readOnly = true)
	public List<Product> getAllProducts() {
		return this.productDao.findAllProducts();
	}
	
	@Transactional(readOnly = true)
	public List<Category> getAllCategories() {
		return this.categoryDao.findAllCategories();
	}
	
	public Optional<Product> getProduct(Long productId) {
		return this.productDao.findById(productId);
	}
	
	public void saveProduct(RequestDto request) {
		Product newProduct = new Product();
		newProduct.setProductName(request.getProductName());
		newProduct.setReference(request.getReference());
		newProduct.setPrice(Float.parseFloat(request.getPrice()));
		newProduct.setWeight(Float.parseFloat(request.getWeight()));
		newProduct.setStock(request.getStock());
		newProduct.setCreationDate(LocalDate.now());
		newProduct.setCategory(request.getCategory());
		this.productDao.save(newProduct);
	}
	
	@Transactional
	public boolean updateProduct(RequestDto request) {
		Optional<Product> optProduct = this.getProduct(request.getProductId());
		if (!optProduct.isPresent()) {
			return false;			
		}
		
		this.productDao.updateProduct(request.getProductName(), request.getReference(), Float.parseFloat(request.getPrice()), Float.parseFloat(request.getWeight()), request.getStock(), request.getCategory(), request.getProductId());
		return true;
	}
	
	@Transactional
	public void deleteProduct(Long productId) {
		Optional<Product> optProduct = this.getProduct(productId);
		if (optProduct.isPresent()) {
			this.productDao.delete(optProduct.get());
		}
	}

	public Map<String, String> validateSale(Long productId, int amount) {
		Map<String, String> messages = new HashMap<>();
		
		Optional<Product> optProduct = this.getProduct(productId);
		if (!optProduct.isPresent()) {
			messages.put("message", "El producto: " + optProduct.get().getProductName() + " con Id: " + productId + " no existe");
		}

		int currentStock = optProduct.get().getStock();
		if ((currentStock == 0) || (amount > currentStock)) {
			messages.put("message", "ERROR: No se puede realizar la venta - El stock es '0' o cantidad es mayor al stock");
		} else if (amount < 0) {
			messages.put("message", "ERROR: El valor de la cantidad debe ser positivo");
		}

		return messages;
	}

	@Transactional
	public void saleProduct(Long productId, int amount) {
		Optional<Product> optProduct = this.getProduct(productId);
		optProduct.ifPresent(p -> {
			int newAmount = optProduct.get().getStock() - amount;
			this.productDao.saleProduct(newAmount, productId);	
		});
	}
}
