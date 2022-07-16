package com.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.app.models.Category;

public class SaveProductDto {
	@NotEmpty
	private String productName;
	
	@NotEmpty
	private String reference;
	
	@NotEmpty
	private String price;
	
	@NotNull
	private Integer stock;
	
	@NotNull
	private Category category;
	
	public SaveProductDto() {}
	
	public SaveProductDto(String productName, String reference, String price, Integer stock, Category category) {
		this.productName = productName;
		this.reference = reference;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
