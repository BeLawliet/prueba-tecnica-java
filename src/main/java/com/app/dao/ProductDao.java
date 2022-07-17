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
	@Query(value = "UPDATE Product p SET p.productName = ?1, p.reference = ?2, p.price = ?3, p.weight = ?4, p.stock = ?5, p.category = ?6 WHERE p.productId = ?7")
	public void updateProduct(String productName, String reference, Float price, Float weight, int stock, Category category, Long productId);

	@Modifying
	@Query(value = "UPDATE Product p SET p.stock = ?1 WHERE p.productId = ?2")
	public void saleProduct(int newStock, Long productId);
}
