package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//@서비스 어노테이션을 추가하면 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional	
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword(); //1234 원문
		String encPassword = encoder.encode(rawPassword); //해쉬 $2a$10$DBe5iG603DF5vciV7mNw2u2YyrEvOrTR0WPn0XDwS/y5pO6sWVrOG
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고, 영속화된 User 오브젝트를 수정
		//select 를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서
		//영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다. 
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		//회원수정 함수 종료 시 == 서비스 종료 == 트랜잭션 종료 == commit이 자동으로 됩니다. 
		//영속화된 persistance 객체의 변화가 감지되면 더티체킹되어  변화된 것들을 update문을 날려줌. 
	}
	

}

/*
@Transactional(readOnly = true) //Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)	
public User 로그인(User user) {
	//return userRepository.save(user);
	return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
}
*/