package com.example.library.dto;

import com.example.library.constant.StockOk;
import com.example.library.constant.TypeOk;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchDto {
	
	private String searchDateType;
	private StockOk stockOk;
	private TypeOk typeOk;
	private String searchBy;
	private String searchQuery = "";
	
}
