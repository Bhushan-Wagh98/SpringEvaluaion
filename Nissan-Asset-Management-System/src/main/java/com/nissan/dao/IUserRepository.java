package com.nissan.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
//	JPQL
//	Stored Procedure		email password
	User findOneByUserNameIgnoreCaseAndPassword(String userName, String password);

//	Stored Procedure		email
	List<User> findAllByEmailIgnoreCase(String email);

//	Stored Procedure		userName
	List<User> findAllByUserNameIgnoreCase(String userName);

//	Stored Procedure		phoneNumber
	List<User> findAllByPhoneNumberIgnoreCase(String phoneNumber);

//	find all 
	List<User> findAll();

//	find by user Id
	User findOneByUserId(Integer userId);
}
