package com.app.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.app.models.Product;

@Repository
public interface ProductDao extends CrudRepository<Product, Long> {
	@Query(value = "SELECT p FROM Product p ORDER BY p.productId ASC")
	public List<Product> findAllProducts();
	
	public Optional<Product> findByProductId(Long productId);
}
