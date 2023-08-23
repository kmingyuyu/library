package com.example.library.dto;

import com.example.library.constant.TypeOk;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainBookDto {
	
	private Long id;
	
	private String bookName; 
	
	private String writer; 
	
	private String pubDate;
	
	private String publisher;
	
	private TypeOk typeOk;
	
	private String imgUrl;
	
	private int borrowCount;
	
	@QueryProjection
	public MainBookDto(Long id ,String bookName,String writer ,String pubDate ,String publisher ,String imgUrl , TypeOk typeOk , int borrowCount) {
		this.id = id;
		this.bookName = bookName;
		this.writer = writer;
		this.pubDate = pubDate;
		this.publisher = publisher;
		this.imgUrl = imgUrl;
		this.typeOk = typeOk;
		this.borrowCount = borrowCount;
		
	}
	
}
