package com.jsp.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.springapp.exception.ProductNotFoundException;
import com.jsp.springapp.model.Product;
import com.jsp.springapp.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	public void addProduct(Product product) {
		repo.save(product);
	}

	public Product findProductById(int prodId) {
		return repo.findById(prodId).orElseThrow(() -> new ProductNotFoundException("Product not found with prodId: "+prodId));
	}

	public void deleteProduct(int prodId) {
		if(!repo.existsById(prodId)) {
			throw new ProductNotFoundException("Product not found with prodId: "+prodId);
		}
		repo.deleteById(prodId);
	}

	public void saveOrUpdateProduct(int prodId, Product updateProduct) {	
		if(repo.existsById(prodId)) {		
			Product existingProduct = repo.findById(prodId).get();
			
			existingProduct.setName(updateProduct.getName());
			existingProduct.setDesg(updateProduct.getDesg());
		    existingProduct.setBrand(updateProduct.getBrand());
		    existingProduct.setPrice(updateProduct.getPrice());
		    existingProduct.setCategory(updateProduct.getCategory());
		    existingProduct.setReleasedDate(updateProduct.getReleasedDate());
		    existingProduct.setAvailable(updateProduct.isAvailable());
		    existingProduct.setQuantity(updateProduct.getQuantity());
		    
		    repo.save(existingProduct);	
		}else {
			repo.save(updateProduct);
		}
	}

	public void deleteAllProducts() {
		repo.deleteAll();
	}
	
}
