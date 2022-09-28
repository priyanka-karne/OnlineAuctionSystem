package com.app.serviceimpl;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Product;
import com.app.repository.ProductRepository;
import com.app.service.IProductService;




@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductRepository prepo;
	@Autowired
	private ModelMapper map;
	
	@Override
	  public List<Product> getAllProduct() {
	    
	    return prepo.findAll();
	  }
	
	@Override
	public Product getProductById(long id) {
		System.out.println("In service");
	Product p= prepo.findById(id);
	return p;
	}
	@Override
	public String addNewProduct(Product product) {
		
		 prepo.save(product);
		 return "Product Addes Succesfully";
	}
	@Override
	public Product updateProduct(long id, Product product) {
		if(prepo.existsById(id)) {
			 prepo.save(product);
			
		}
		return null;
	}
	@Override
	public String deleteProduct(long id) {
		if(prepo.existsById(id)) {
		Product p=	prepo.findById(id);
			 prepo.deleteById(id);
			 return "Product Deleted Succesfully";
			
		}
		return "Product does not exist";
	}

	

}
