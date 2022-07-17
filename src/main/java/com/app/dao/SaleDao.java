package com.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.app.models.Sale;

@Repository
public interface SaleDao extends CrudRepository<Sale, Long> {
       
}
