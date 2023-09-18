package com.assignment.crudapi.Service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.crudapi.Model.UserModel;
import com.assignment.crudapi.Repository.UserRepository;
import com.assignment.crudapi.Service.UserService;
import com.assignment.crudapi.Exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserModel saveUser(UserModel userModel) {
		return userRepo.save(userModel);
	}

	@Override
	public List<UserModel> getallUsers() {
		return userRepo.findAll();
	}

	@Override
	public UserModel getUserbyId(String Id) {
		return userRepo.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", Id));
	}

	@Override
	public UserModel updateUserbyId(UserModel userModel, String Id) {
		UserModel existingUser = this.getUserbyId(Id);
		existingUser.setAge(userModel.getAge());
		existingUser.setFirst_name(userModel.getFirst_name());
		existingUser.setLast_name(userModel.getLast_name());
		userRepo.save(existingUser);
		return existingUser;
	}

	@Override
	public void deleteUser(String Id) {
		userRepo.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User","Id", Id));
		userRepo.deleteById(Id);
	}
	

	
}
