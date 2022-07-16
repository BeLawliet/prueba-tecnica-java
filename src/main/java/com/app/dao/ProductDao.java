package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Category;
import com.app.models.Product;

@Repository
public interface ProductDao extends CrudRepository<Product, Long> {
	@Query(value = "SELECT p FROM Product p ORDER BY p.productId ASC")
	public List<Product> findAllProducts();
	
	public Optional<Product> findByProductId(Long productId);
	
	@Modifying
	@Query(value = "UPDATE Product p SET p.productName = ?1, p.reference = ?2, p.price = ?3, p.stock = ?4, p.category = ?5 WHERE p.productId = ?6")
	public void updateProduct(String productName, String reference, Float price, int stock, Category category, Long productId);
}
