package com.example.library.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.example.library.constant.StockOk;
import com.example.library.constant.TypeOk;
import com.example.library.entity.Book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookFormDto {
	
	private Long id;
	
	@NotBlank(message = "제목을 입력 해주세요")
	private String bookName;
	
	@NotNull(message = "작가명을 입력 해주세요")
	private String writer;

	@NotNull(message = "출판사를 입력 해주세요")
	private String publisher;
	
	@NotNull(message = "출간일을 입력 해주세요")
	private String pubDate;
	
	@NotNull(message = "간략 줄거리를 입력 해주세요")
	private String story;
	
	@NotNull(message = "카테고리를 설정 해주세요")
	private TypeOk typeOk; 
	
	private StockOk stockOk = StockOk.Y;
	
	private int borrowCount = 0;
	
//	상품 이미지 정보를 저장
	private List<BookImgDto> bookImgDtoList = new ArrayList<>();

	
//	상품이미지 아이디를 저장 : 수정시 이미지 아이디 담아둘 용도
	private List<Long> bookImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
//	dto -> entity 변환
	public Book createBook() {
		return modelMapper.map(this, Book.class);
	}
	
//	entity -> dto 변환
	public static BookFormDto of(Book book) {
		return modelMapper.map(book, BookFormDto.class);
	}
	
}
