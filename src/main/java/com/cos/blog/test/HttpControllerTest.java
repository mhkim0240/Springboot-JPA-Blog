package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//기본적으로 사용자의 요청에 응답하는 것을 Controller 라고 한다. 

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG ="HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m = new Member(1,"아이유","1234","email");
		
		Member m = Member.builder().username("살라").password("2345").build();
		/*
		System.out.println(TAG + "getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "setter : " + m.getId());
		*/
		
		System.out.println(TAG + "getter : " + m.getUsername());
		m.setUsername("홀란");
		System.out.println(TAG + "setter : " + m.getUsername());
		
		return "lombok test 완료";
	}
	
	//인터넷 브라우저 요청은 get만 가능하다. 
	//http://localhost:8080/http/get (select)
	
	//http://localhost:8080/http/get?id=1&username=아이유
	//http://localhost:8080/http/get?id=1&username=아이유&password=1234&email=dev.mhkim0240@gmail.com
	
	@GetMapping("/http/get")	
	//public String getTest(@RequestParam int id, @RequestParam String username) { //MessageConverter (스프링부트)
	public String getTest(Member m) {
		//return "get request, parma : " + id + ", username : " + username;
		return "get request, id : " + m.getId() + ", username : " + m.getUsername()+ ", password : " + m.getPassword()+ ", email : " + m.getEmail();
	}
	
	//x-www-form-urlencoded
	//public String postTest(Member m) {
	
	//raw
	//public String postTest(String text) {
	
	//text/plain
	//public String postTest(@RequestBody String text) {

	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") //raw - text/plain, application/json
	//application/json
	public String postTest(@RequestBody Member m) {	//MessageConverter (스프링부트)
		//return "post request";
		
		//x-www-form-urlencoded
		//return "post request, id : " + m.getId() + ", username : " + m.getUsername()+ ", password : " + m.getPassword()+ ", email : " + m.getEmail();
		
		//raw - text/plain
		//return "post request : " + text;
		
		//raw - application/json
		return "post request, id : " + m.getId() + ", username : " + m.getUsername()+ ", password : " + m.getPassword()+ ", email : " + m.getEmail();
	}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		//return "put request";
		return "put request, id : " + m.getId() + ", username : " + m.getUsername()+ ", password : " + m.getPassword()+ ", email : " + m.getEmail();
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete request";
	}
}
