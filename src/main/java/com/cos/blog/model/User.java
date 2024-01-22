package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


//ORM -> Java(다른언어) Object->테이블로 매핑. 
@Entity
public class User {

	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전량을 따라간다. 
	private int id; 		//시퀀스, auto_increment
	
	@Column(nullable = false, length=30)
	private String username; //아이디
	
	@Column(nullable = false, length = 100) //123456 => 해쉬 (비번 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private  String email;
	
	@ColumnDefault("'user'")
	private String role; //Enum을 쓰는게 좋다. //admin, user, manager
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;	
	
}
