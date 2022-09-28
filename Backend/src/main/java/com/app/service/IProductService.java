package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.Product;

public interface IProductService {

	public List<Product> getAllProduct();
	
    public Product getProductById(long id);
	
	public String addNewProduct(Product product);
	
     public	 Product updateProduct(long id,Product product);
     
      public	String deleteProduct(long id); 
}
