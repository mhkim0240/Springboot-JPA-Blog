package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"}) //아무것도 안붙였을 때와 /를 붙였을때 index 로 가게. 
	public String index(Model model) {
		
		model.addAttribute("boards", boardService.글목록());
		//model은 jsp 에서 리퀘스트 정보이다. 

		return "index"; //viewResolver 작동. 
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
