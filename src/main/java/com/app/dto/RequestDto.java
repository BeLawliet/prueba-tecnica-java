package com.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.app.models.Category;

public class RequestDto {
	private Long productId;
	
	@NotEmpty
	private String productName;
	
	@NotEmpty
	private String reference;
	
	@NotEmpty
	private String price;

	@NotNull
	private String weight;
	
	@NotNull
	private Integer stock;
	
	@NotNull
	private Category category;
	
	public RequestDto() {}
	
	public RequestDto(Long productId, String productName, String reference, String price, String weight, Integer stock, Category category) {
		this.productId = productId;
		this.productName = productName;
		this.reference = reference;
		this.price = price;
		this.weight = weight;
		this.stock = stock;
		this.category = category;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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
