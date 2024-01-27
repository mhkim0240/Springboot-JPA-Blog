package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class UserApiController {

	@Autowired // DI  의존성 주입. 
	private UserRepository userRepository;
	
	@PostMapping("/api/user")
	//public int save(@RequestBody User user) {
	public ResponseDto<Integer> save(@RequestBody User user) {
		
		System.out.println("User ApiController : save 호출됨");
		
		/*
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		userRepository.save(user);
		return 1;
		*/
		
		return new ResponseDto<Integer>(HttpStatus.OK,1); //자바오브젝트를 JSON으로 변환해서 리턴(JACKSON)
	}
	
}
