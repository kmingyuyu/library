package com.example.library.entity;


import com.example.library.constant.StockOk;
import com.example.library.constant.TypeOk;
import com.example.library.dto.BookFormDto;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
@Entity
@Table(name="book")
public class Book extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="book_id")
	private Long id;
	
	@Column(nullable = false)
	private String bookName; //책 제목
	
	@Column(nullable = false)
	private String writer; //작가
	
	@Column(nullable = false)
	private String publisher; //출판사
	
	@Enumerated(EnumType.STRING)
	private TypeOk typeOk; //책 분류
	
	@Column(nullable = false)
	private String pubDate; //출간일
	
	@Lob
	@Column(nullable = false , columnDefinition = "longtext")
	private String story; //간략 줄거리
	
	@Enumerated(EnumType.STRING)
	private StockOk stockOk; //책 상태
	
	private int borrowCount ; //총 대출수
	
	public void updateBook(BookFormDto bookFormDto) {
		this.bookName = bookFormDto.getBookName();
		this.writer = bookFormDto.getWriter();
		this.publisher = bookFormDto.getPublisher();
		this.typeOk = bookFormDto.getTypeOk();
		this.pubDate = bookFormDto.getPubDate();
		this.story = bookFormDto.getStory();
		this.stockOk = bookFormDto.getStockOk();
	}
	
	
	
}
