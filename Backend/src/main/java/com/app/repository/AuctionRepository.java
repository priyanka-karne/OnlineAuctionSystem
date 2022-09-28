package com.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.Auction;
import com.app.entity.Product;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

	Auction findByProduct(Product product);

	public Auction findById(long id);
//	
//	@Transactional
//	@Modifying
//	@Query("Delete u from Users u where u.email=?1")
//   public Auction deleteById(long id);
//	public boolean existsById(long id);


		
	
}
