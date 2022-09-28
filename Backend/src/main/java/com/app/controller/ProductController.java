package com.app.controller;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dto.Response;
import com.app.entity.Auction;
import com.app.entity.Product;
import com.app.entity.Users;
import com.app.serviceimpl.ProductServiceImpl;
@RestController
@CrossOrigin("http://localhost:3000")
@Transactional
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl pservice;
	
	@GetMapping
	  public ResponseEntity<?> seeAllProduct() {
		System.out.println("in get all products");
	    List<Product> result = pservice.getAllProduct();
	    return  Response.success(result);
	  }
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) {
		Product result = pservice.getProductById(id);
		 
		if(result == null)
			return Response.error("product not found");
		return Response.success(result);
	}
	
	@PostMapping("/addproduct")
	public ResponseEntity<?>  addProduct(@RequestBody Product prod) 
	{
		System.out.println("product insertion  " + prod);
		String s=this.pservice.addNewProduct(prod);
		return Response.success(s);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long productid) {
		System.out.println("in get Product ");
		String d=pservice.deleteProduct(productid);
		return Response.success(d);
	}
	
	
	@PutMapping("/updateproduct")
	public ResponseEntity<?> updateAuction(@PathVariable ("id") long id,@RequestBody Product product)
	{
		pservice.updateProduct(id, product);
		return Response.success(product);
	}
	

}
