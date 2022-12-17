package com.nissan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.dao.IUserRepository;
import com.nissan.dto.LogInRequestDTO;
import com.nissan.dto.SignUpRequestDTO;
import com.nissan.model.User;
import com.nissan.model.UserType;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public APIResponse signUp(SignUpRequestDTO signUpRequestDto) {
		APIResponse apiResponse = new APIResponse();
//		DTO to Entity	----> SignUpRequestDTO to User
//		Autowired / anonymous
		User user = new User(); // Avoided either use Autowired --anonymous

		user.setFirstName(signUpRequestDto.getFirstName());
		user.setLastName(signUpRequestDto.getLastName());
		user.setAge(signUpRequestDto.getAge());
		user.setGender(signUpRequestDto.getGender());
		user.setAddress(signUpRequestDto.getAddress());
		user.setPhoneNumber(signUpRequestDto.getPhoneNumber());
		user.setEmail(signUpRequestDto.getEmail());
		user.setPassword(signUpRequestDto.getPassword());
		user.setUserName(signUpRequestDto.getUserName());
		user.setUserTypeId(signUpRequestDto.getUserTypeId());

//		check phone number is existing or not
		List<User> checkByPhone = userRepository.findAllByPhoneNumberIgnoreCase(signUpRequestDto.getPhoneNumber());

		if (!checkByPhone.isEmpty()) {
			apiResponse.setData("Phone Number is Already Present!");
			return apiResponse;
		}

//		check email is existing or not
		List<User> checkByEmail = userRepository.findAllByEmailIgnoreCase(signUpRequestDto.getEmail());

		if (!checkByEmail.isEmpty()) {
			apiResponse.setData("Email is Already Present!");
			return apiResponse;
		}

//		check username is existing or not
		List<User> checkByUserName = userRepository.findAllByUserNameIgnoreCase(signUpRequestDto.getUserName());

		if (!checkByUserName.isEmpty()) {
			apiResponse.setData("UserName is Already Present!");
			return apiResponse;
		}

//		check gender is correct or not
		if (!signUpRequestDto.getGender().equalsIgnoreCase("Male")
				&& !signUpRequestDto.getGender().equalsIgnoreCase("Female")
				&& !signUpRequestDto.getGender().equalsIgnoreCase("Other")) {
			apiResponse.setData("Invalid Gender!");
			return apiResponse;
		}

//		check role
		if (signUpRequestDto.getUserTypeId() != 1 && signUpRequestDto.getUserTypeId() != 2) {
			apiResponse.setData("Invalid role!");
			return apiResponse;
		}

//		check age
		if (signUpRequestDto.getAge() < 18 && signUpRequestDto.getAge() > 60) {
			apiResponse.setData("Invalid role!");
			return apiResponse;
		}

//		Save to Entity ---- ORM -- Database
		userRepository.save(user);

//		Storing more details
		Map<String, Object> data = new HashMap<>();

		data.put("userId", user.getUserId());
		data.put("firstName", user.getFirstName());
		data.put("lastName", user.getLastName());
		data.put("age", user.getAge());
		data.put("gender", user.getGender());
		data.put("email", user.getEmail());
		data.put("phoneNumber", user.getPhoneNumber());
		data.put("address", user.getAddress());
		data.put("userTypeId", user.getUserTypeId());

		apiResponse.setData(data);

		return apiResponse;
	}

	@Override
	public APIResponse login(LogInRequestDTO loginRequestDTO) {
		APIResponse apiResponse = new APIResponse();

//		verify user exist with given email and password
		User user = userRepository.findOneByUserNameIgnoreCaseAndPassword(loginRequestDTO.getUserName(),
				loginRequestDTO.getPassword());

//		response
		if (user == null) {
			apiResponse.setData("User Login Failed!");
			return apiResponse;
		}

//		Storing more details
		Map<String, Object> data = new HashMap<>();
//		data.put("userTypeId", user.getUserTypeId());
//		data.put("email", user.getEmail());

		data.put("userId", user.getUserId());
		data.put("firstName", user.getFirstName());
		data.put("lastName", user.getLastName());
		data.put("age", user.getAge());
		data.put("gender", user.getGender());
		data.put("email", user.getEmail());
		data.put("phoneNumber", user.getPhoneNumber());
		data.put("address", user.getAddress());
		data.put("userType", user.getUserType());

		apiResponse.setData(data);

		return apiResponse;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> checkByPhone = userRepository.findAll();
		return checkByPhone;
	}

	@Override
	public APIResponse searchById(Integer userId) {
		APIResponse apiResponse = new APIResponse();

//		verify user exist with given email and password
		User user = userRepository.findOneByUserId(userId);

//		response
		if (user == null) {
			apiResponse.setData("User Login Failed!");
			return apiResponse;
		}

//		Storing more details
		Map<String, Object> data = new HashMap<>();
//		data.put("userTypeId", user.getUserTypeId());
//		data.put("email", user.getEmail());

		data.put("userId", user.getUserId());
		data.put("firstName", user.getFirstName());
		data.put("lastName", user.getLastName());
		data.put("age", user.getAge());
		data.put("gender", user.getGender());
		data.put("email", user.getEmail());
		data.put("phoneNumber", user.getPhoneNumber());
		data.put("address", user.getAddress());
		data.put("userType", user.getUserType());

		apiResponse.setData(data);

		return apiResponse;
	}

	@Override
	public APIResponse deleteUserById(Integer userId) {
		APIResponse apiResponse = new APIResponse();

		User user = userRepository.findOneByUserId(userId);

//		response
		if (user == null) {
			apiResponse.setData("User Not found!");
			return apiResponse;
		}

		userRepository.delete(user);

		apiResponse.setData("User Deleted Successfully!");

		return apiResponse;
	}
}
