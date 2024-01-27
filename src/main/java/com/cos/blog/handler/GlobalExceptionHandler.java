package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

//이 처리를 안하면 Whitelabel Error Page 를 리턴함. 

@ControllerAdvice //모든 Exception이 발생했을 때 이 클래스로 들어오게 하려면. 이 어노테이션을 추가함. 
@RestController
public class GlobalExceptionHandler {
	///*
	//모든 Exception 처리. 
	@ExceptionHandler(value=Exception.class) //이 클래스로 들어왔을 때 어떤 Exception만 받을 거냐면...  
	public ResponseDto<String> handleArgumentException(Exception e) {
		
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()); //자바오브젝트를 JSON으로 변환해서 리턴(JACKSON)
		//return "<Color='ff000000'><b><h1>" + e.getMessage() + "</h1></b></Color>";
	}
	
	/*
	//IllegalArgumentException 때 처리. 
	@ExceptionHandler(value=IllegalArgumentException.class) //이 클래스로 들어왔을 때 어떤 Exception만 받을 거냐면...  
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
//*/
}
