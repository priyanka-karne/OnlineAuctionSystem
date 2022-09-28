package com.app.service;

import java.util.List;

import com.app.Dto.*;
import com.app.entity.Users;

public interface IUserService {
	
	
	public UserDto getUserById(long  id);

	public List<Users> getAllUser() ;
	
	public Users getByEmail(String email);
	
	public Users addNewUser(Users user);
	
	public Users updateUser(long id,Users user);
	
public	String deleteUser(long id);

public String saveUserDetail(UserDto userDto);

public Users ValidateUSer(String email,String password);

}
