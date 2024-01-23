package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;



@RestController
public class DummyControllerTest {
	
	@Autowired // DI  의존성 주입. 
	private UserRepository userRepository;
	
	//http://localhost:8080/blog/dummy/users
	@GetMapping("dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한페이지당 2건 조회. 
	//http://localhost:8080/blog/dummy/user?page=0,1
	@GetMapping ("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		if(pagingUser.isLast()) {
			
		}
		List<User> users = pagingUser.getContent();	
	
		return users;
	}
	
	//http://localhost:8080/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될것 아냐?
		//그럼 return null이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		//Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해!!
		
		//AOP Exception 을 가로채서 에러 페이지를 표출. 
		//방법3 -> 선호하는 방법	
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :  " + id);
			}
		});
		
		
		/*
		//방법5 -> 람다식
		User user = userRepository.findById(id).orElseThrow(()-> {
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :  " + id);
			}
		});
		*/
		
		/*	//방법4
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
				return new User();
			}
		});
		*/
		//방법1
		//User user = userRepository.findById(id).get();
		/* 방법2
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
				//TODO Auto-generated method stub
				return new User();
			}
		});
		*/
		
		//요청 : 웹브라우저. 
		//user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터)->json (Gson 라이브러리) 
		//스프링부트 = MessageConverter라는 애가 응답시에 자동 작동 
		//만약에 자바 오브젝트를 리턴하게 되면 Messageconverter가 Jackson 라이브러리를 호출해서 
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다. 
		return user;
	}

	//http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username, password, email 데이터를 가지고 (요청)
	
	//public String join(RequestParam("username") String u, )
	//public String join(String username, String password,String email) { //xwww-formurl -> key=value 약속된 규칙
	@PostMapping("/dummy/join")
	public String join(User user) { //xwww-formurl -> key=value 약속된 규칙
		//System.out.println("username : " + username);
		//System.out.println("password : " + password);
		//System.out.println("email : " + email);
		
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		
		System.out.println("role : " + user.getRole());
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
	
	
}
