package com.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import com.app.dto.RequestDto;
import com.app.models.Category;
import com.app.models.Product;
import com.app.services.AppService;

@Controller
public class MainController {
	@Autowired
	private AppService service;
	
	// List products
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
	
	// Save products
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
	
	// Update products
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
	
	// Delete products
	@GetMapping(value = "delete-product/{id}")
	public String deleteProduct(@PathVariable String id) {
		this.service.deleteProduct(Long.parseLong(id));
		return "redirect:/";
	}

	// Sale products
	@GetMapping(value = "sale-product")
	public String formSaleProduct(Model model) {
		model.addAttribute("title", "Sale products");
		return "sales";
	}

	@PostMapping(value = "sale-product")
	public String saleProduct(@RequestParam String productId, @RequestParam String amount, Model model) {
		Map<String, String> messages = new HashMap<>();
		
		if ((productId.trim().length() <= 0) || (amount.trim().length() <= 0)) {
			messages.put("message", "Debe ingresar los valores");
			model.addAttribute("messages", messages);
			return "sales";
		}
		
		long idProduct = Long.parseLong(productId);
		int amountSent = Integer.parseInt(amount);

		messages = this.service.validateSale(idProduct, amountSent);
		if (messages.size() > 0) {
			model.addAttribute("messages", messages);
			return "sales";
		}

		this.service.saleProduct(idProduct, amountSent);
		return "redirect:/";
	}
}
