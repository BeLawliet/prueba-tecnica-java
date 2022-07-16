package com.app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.CategoryDao;
import com.app.dao.ProductDao;
import com.app.dto.SaveProductDto;
import com.app.models.Category;
import com.app.models.Product;

@Service
public class AppService {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Product> getAllProducts() {
		return this.productDao.findAllProducts();
	}
	
	public List<Category> getAllCategories() {
		return this.categoryDao.findAllCategories();
	}
	
	public Optional<Product> getProduct(Long productId) {
		return this.productDao.findById(productId);
	}
	
	public void saveProduct(SaveProductDto request) {
		Product newProduct = new Product();
		newProduct.setProductName(request.getProductName());
		newProduct.setReference(request.getReference());
		newProduct.setPrice(Float.parseFloat(request.getPrice()));
		newProduct.setStock(request.getStock());
		newProduct.setCreationDate(LocalDate.now());
		newProduct.setCategory(request.getCategory());
		this.productDao.save(newProduct);
	}
}