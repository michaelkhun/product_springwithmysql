package com.jsp.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jsp.springapp.model.Product;
import com.jsp.springapp.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/getall")
	public List<Product> getAllProducts(){
		return service.getAllProducts();
	}
	
	@PostMapping("/add")
	public void addProduct(@RequestBody Product product) {
		 service.addProduct(product);
	}
	
	@GetMapping("/find/{prodId}")
	public Product findProductById(@PathVariable int prodId) {
		return service.findProductById(prodId);
	}
	
	@DeleteMapping("delete/{prodId}")
	public void deleteProduct(@PathVariable int prodId) {
		service.deleteProduct(prodId);
	}
	
	@PutMapping("update/{prodId}")
	public void saveOrUpdateProduct(@PathVariable int prodId, @RequestBody Product updateProduct) {
		service.saveOrUpdateProduct(prodId, updateProduct);
	}
	
	@DeleteMapping("/deleteall")
	public void deleteAll() {
		service.deleteAllProducts();
	}
}
