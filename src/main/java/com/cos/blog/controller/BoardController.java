package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	
	@GetMapping({"","/"}) //아무것도 안붙였을 때와 /를 붙였을때 index 로 가게. 
	public String index() {
		//WEB-INF/views/index.jsp
		return "index";
	}
	
}
