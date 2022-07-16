package com.app.controllers;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.app.dto.RequestDto;
import com.app.models.Category;
import com.app.models.Product;
import com.app.services.AppService;

@Controller
public class MainController {
	@Autowired
	private AppService service;
	
	@ModelAttribute("products")
	public List<Product> getProducts() {
		return this.service.getAllProducts();
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return this.service.getAllCategories();
	}
	
	@GetMapping(path = "/")
	public String index(Model model) {
		model.addAttribute("title", "Página Principal");
		return "index";
	}
	
	@GetMapping(value = "form-product")
	public String formProduct(Model model) {
		model.addAttribute("title", "New Product");
		model.addAttribute("requestDto", new RequestDto());
		return "form";
	}
	
	@PostMapping(value = "save-product")
	public String saveProduct(@Valid RequestDto request, BindingResult result) {
		if (result.hasErrors()) {
			return "form";
		}
		
		this.service.saveProduct(request);
		return "redirect:/";
	}
	
	@GetMapping(value = "update-product/{id}")
	public String formUpdate(@PathVariable String id, Model model) {
		model.addAttribute("title", "Update Product");
		
		Optional<Product> optProduct = this.service.getProduct(Long.parseLong(id));
		optProduct.ifPresent(p -> {
			model.addAttribute("requestDto", p);
		});
		
		return "update";
	}
	
	@PostMapping(value = "update-product")
	public String updateProduct(@Valid RequestDto request, BindingResult result) {
		if (result.hasErrors()) {
			return "update";
		}
		
		if (!this.service.updateProduct(request)) {
			return "update";
		}
		
		return "redirect:/";
	}
}