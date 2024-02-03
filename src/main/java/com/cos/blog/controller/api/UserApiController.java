package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired // DI  의존성 주입. 
	private UserService userService;
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	//@PostMapping("/api/user")
	@PostMapping("/auth/joinProc")
	//public int save(@RequestBody User user) {
	public ResponseDto<Integer> save(@RequestBody User user) {
		
		System.out.println("User ApiController : save 호출됨");
		
		//user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변환해서 리턴(JACKSON)
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user, HttpSession session){ //RequestBody 가 안걸려있으면 json데이터를 못받는다.
		System.out.println("User ApiController : update 호출됨");
		userService.회원수정(user);

		//여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음. 
		//하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임. 
		//강제로 Authentication 객체를 만들어서 세션 내부의 Authentication이 저장되는 영역에 넣어주면 된다.
		
		UserDetails userDetail = principalDetailService.loadUserByUsername(user.getUsername());
		Authentication authToken = new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
		SecurityContext secuContext = SecurityContextHolder.getContext();
		secuContext.setAuthentication(authToken);
		session.setAttribute("SPRING_SECURITY_CONTEXT", secuContext);
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}

//@Autowired
//private SecurityConfig sconfig;
//sconfig.updatePassword(encPassword);


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