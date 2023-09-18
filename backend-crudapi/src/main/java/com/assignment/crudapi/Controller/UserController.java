package com.assignment.crudapi.Controller;
import java.util.List;

import org.springframework.http.HttpStatus;
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
import com.assignment.crudapi.Model.UserModel;
import com.assignment.crudapi.Service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/users")
public class UserController {
	private UserService userservice;
	
	public UserController(UserService userservice) {
		super();
		this.userservice = userservice;
	}
	
	@PostMapping("/add")
	public ResponseEntity<UserModel> SaveUser(@RequestBody UserModel userModel){
		return new ResponseEntity<UserModel>(userservice.saveUser(userModel),HttpStatus.CREATED);
	}
	
	@GetMapping("/allusers")
	public List<UserModel> AllUsers(){
		return userservice.getallUsers();
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<UserModel> GetUser(@PathVariable("email") String email){
		return new ResponseEntity<UserModel>(userservice.getUserbyId(email),HttpStatus.OK);
	}
	
	@PutMapping("/{email}")
	public ResponseEntity<UserModel> UpdateUser(@PathVariable("email") String email, @RequestBody UserModel userModel){
		return new ResponseEntity<UserModel>(userservice.updateUserbyId(userModel,email),HttpStatus.OK);
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<String> DeleteUser(@PathVariable("email") String email){
		userservice.deleteUser(email);
		return new ResponseEntity<String>("User Deleted",HttpStatus.OK);
	}
}


