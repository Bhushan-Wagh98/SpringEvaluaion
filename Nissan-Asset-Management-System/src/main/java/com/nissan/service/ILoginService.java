package com.nissan.service;

import java.util.List;

import com.nissan.common.APIResponse;
import com.nissan.dto.LogInRequestDTO;
import com.nissan.dto.SignUpRequestDTO;
import com.nissan.model.User;

public interface ILoginService {
//	SignUp
	APIResponse signUp(SignUpRequestDTO signUpRequestDto);

//	LogIn
	APIResponse login(LogInRequestDTO loginRequestDto);

//	get all users
	List<User> getAllUsers();

//	search by Id
	APIResponse searchById(Integer userId);

//	delete user
	APIResponse deleteUserById(Integer userId);
}
