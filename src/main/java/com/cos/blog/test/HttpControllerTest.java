package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

//기본적으로 사용자의 요청에 응답하는 것을 Controller 라고 한다. 

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	@GetMapping("/http/get")	
	public String getTest() {
		return "get request";
	}
	
	@PostMapping("/http/post")
	public String postTest() {
		return "post request";
		}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put request";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete request";
	}
}
