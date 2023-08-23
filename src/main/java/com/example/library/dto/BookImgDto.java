package com.example.library.dto;

import com.example.library.constant.TypeOk;
import com.example.library.entity.BookImg;

import lombok.Getter;
import lombok.Setter;

import org.modelmapper.ModelMapper;

@Getter
@Setter
public class BookImgDto {
	
	private Long id;
	
	private String oriImgName;
	
	private String imgName;
	
	private String imgUrl;
	
	private TypeOk typeOk;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static BookImgDto of(BookImg bookImg) {
		return modelMapper.map(bookImg, BookImgDto.class);
	}
	
}
