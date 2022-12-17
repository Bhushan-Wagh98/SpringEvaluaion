package com.nissan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.dto.LogInRequestDTO;
import com.nissan.dto.SignUpRequestDTO;
import com.nissan.model.User;
import com.nissan.service.ILoginService;

@CrossOrigin
@RestController
@RequestMapping({ "api/" })
public class LoginController {

	@Autowired
	private ILoginService loginService;

//	SignUp
	@PostMapping("signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
		APIResponse apiResponse = loginService.signUp(signUpRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

//	LogIn
	@PostMapping("login")
	public ResponseEntity<APIResponse> logIn(@RequestBody LogInRequestDTO LoginRequestDTO) {
		APIResponse apiResponse = loginService.login(LoginRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

//	get all users
	@GetMapping("users")
	public List<User> allUsersList() {
		List<User> usersList = loginService.getAllUsers();
		return usersList;
	}

//	get single user by Id
	@GetMapping("users/{userId}")
	public APIResponse allUsersList(@PathVariable Integer userId) {
		APIResponse usersList = loginService.searchById(userId);
		return usersList;
	}

//	delete user by Id
	@DeleteMapping("users/{userId}")
	public APIResponse deleteUser(@PathVariable Integer userId) {
		APIResponse usersList = loginService.deleteUserById(userId);
		return usersList;
	}
}
