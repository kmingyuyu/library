package com.example.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowDto {
	
	@NotNull(message = "도서 아이디는 필수 입력 입니다")
	private Long bookId;
	
}
