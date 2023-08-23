package com.example.library.dto;


import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	private String email;
	
	@NotEmpty(message = "비밀번호는 필수 입력값입니다.")
	@Length(min = 8 , max = 15 , message = "비밀번호는 8자 ~ 16자 사이로 입력 해주세요.")
	private String password;
	
	@NotEmpty(message = "이름은 필수 입력값입니다")
	private String name;
	
	@NotEmpty(message = "생년월일은 필수 입력값입니다")
	private String birth;
	
	@NotEmpty(message = "핸드폰 인증을 완료 해주세요.")
	private String phoneNumber;
	
	private String numStr;
	
}
