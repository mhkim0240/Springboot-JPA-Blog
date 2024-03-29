package com.cos.blog.model;



import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//ORM -> Java(다른언어) Object->테이블로 매핑.
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id; 
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob//대용량 데이터
	private String content; //섬머노트 라이브러리<html>태그가 섞여서 디자인이 됨. 
	
	//@ColumnDefault("0")
	private int count; //조회수
	
	@ManyToOne(fetch=FetchType.EAGER) //Many = Many , User = One
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다. 

	//@JoinColumn(name="replyId")
	
	//UI 구현 상에 리플이 펼쳐보기 형태로 바로 필요하지 않을 때는 LAZY 전략
	//@OneToMany(mappedBy="board",fetch=FetchType.LAZY) //mappedBy 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 컬럼을 만들지마세요.  board = Reply의 멤버 변수 이름.
	
	//UI 처음 화면 상에 리플 내용이 보여지는 UI 라면 EAGER 전략으로 처리 
	@OneToMany(mappedBy="board",fetch=FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 컬럼을 만들지마세요.  board = Reply의 멤버 변수 이름.
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
