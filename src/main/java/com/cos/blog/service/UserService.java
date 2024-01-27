package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//@서비스 어노테이션을 추가하면 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional	
	public void 회원가입(User user) {
		//return userRepository.save(user);
		userRepository.save(user);
		
	}
}
