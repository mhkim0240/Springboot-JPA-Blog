package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//JSP 로 치면 DAO 다. 
//자동으로 bean 등록이 된다. 
//@Repository // 생략가능하다. 
public interface UserRepository extends JpaRepository<User,Integer> {

	//JPA Naming 전략
	//SELECT * FROM user WHERE = ? AND password= ?;
	User findByUsernameAndPassword(String username,String password);
	
	//@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2",nativeQuery = true)
	//User login(String username,String password);
	
}
