package com.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Dto.*;

import com.app.entity.Users;
import com.app.repository.UserRepository;
import com.app.service.IUserService;

import com.app.Dto.*;
@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository urepo;
	@Autowired
	private ModelMapper mapper;
	@Override
	public UserDto getUserById(long id) {
		Users u= urepo.getById(id);
		UserDto map=mapper.map(u, UserDto.class);
				return map;
//		return u;
	
	}
	@Override
	public List<Users> getAllUser() {
		
		return urepo.findAll();
	}
	@Override
	public Users getByEmail(String email) {
		
		return urepo.findByEmail(email);
	}
	@Override
	public Users addNewUser(Users user) {
		return this.urepo.save(user);
		 
	}
	@Override
	public Users updateUser(long id, Users user) {
		if(urepo.existsById(id))
			return urepo.save(user);
		throw new RuntimeException("No such user exists with user id -> "+id);
	}
	@Override
	public String deleteUser(long id) {
		this.urepo.deleteById(id);
		return "User Removed";
	}
	@Override
	public String saveUserDetail(UserDto userDto) {
		Users user=mapper.map(userDto, Users.class);
		Users pUser=urepo.save(user);
		UserDto u= mapper.map(pUser, UserDto.class);
		return "Register Sucess";
		
	}
	@Override
	public Users ValidateUSer(String email, String password) {
		Users u=urepo.findByEmail(email);
		if(u != null && u.getPassword().equals(password))
		{
			return u;
		}
		return null;
	
	}
	
	
	


}
