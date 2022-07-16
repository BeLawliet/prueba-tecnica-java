package com.app.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.app.models.Category;

@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {
	@Query(value = "SELECT c FROM Category c ORDER BY c.categoryId ASC")
	public List<Category> findAllCategories();
}
