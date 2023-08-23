package com.example.library.entity;

import java.time.LocalDateTime;

import groovy.transform.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
@Entity
@Table(name="board")
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int boardNum; //게시물 번호
	
	private String title; //제목
	
	private String content; //내용
	
	private LocalDateTime regDate; //작성일
	
	private LocalDateTime updateDate; //수정일
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
}
