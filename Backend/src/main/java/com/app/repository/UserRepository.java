package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	@Query("select u from Users u where u.email=?1")
	public Users findByEmail(String email);
	public Users findById(long id);
	public Users deleteById(long id);
	public boolean existsById(long id);
}
