package com.assignment.crudapi.Service;

import java.util.List;

import com.assignment.crudapi.Model.UserModel;

public interface UserService {
	
	UserModel saveUser(UserModel userModel);
	List<UserModel> getallUsers();
	UserModel getUserbyId(String email);
	UserModel updateUserbyId(UserModel userModel,String email);
	void deleteUser(String email);
	
}
