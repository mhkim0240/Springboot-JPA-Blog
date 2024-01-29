package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired // DI  의존성 주입. 
	private UserService userService;
	
	@PostMapping("/api/user")
	//public int save(@RequestBody User user) {
	public ResponseDto<Integer> save(@RequestBody User user) {
		
		System.out.println("User ApiController : save 호출됨");
		
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(JACKSON)
	}
	
}




// 이방식은 전통적인 로그인 방식이고 다음시간에 스프링 시큐리티를 이용해서 로그인 해볼거다.
/*
//이렇게 선언해도 가능. 
//@Autowired 
//private HttpSession session;

@PostMapping("/api/user/login")
public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
	System.out.println("UserApiController : login 호출됨");
	User principal = userService.로그인(user); //principal (접근주체)
	
	if(principal != null) {
		session.setAttribute("principal", principal);
	}
	return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(JACKSON)
}
*/




/* Service 없이. 
@RestController
public class UserApiController {

	@Autowired // DI  의존성 주입. 
	private UserRepository userRepository;
	
	@PostMapping("/api/user")
	//public int save(@RequestBody User user) {
	public ResponseDto<Integer> save(@RequestBody User user) {
		
		System.out.println("User ApiController : save 호출됨");
		
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		userRepository.save(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK,1); //자바오브젝트를 JSON으로 변환해서 리턴(JACKSON)
	}
}
*/