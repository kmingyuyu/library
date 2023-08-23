package com.example.library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.library.dto.BorrowDto;
import com.example.library.service.BorrowService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BorrowController {
	
	private final BorrowService borrowService;
	
	@PostMapping("/borrow")
	public @ResponseBody ResponseEntity bookBorrow( 
			@RequestBody @Valid BorrowDto borrowDto , BindingResult bindingResult , Principal principal) {
		

		if(bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			
			for (FieldError fieldError : fieldErrors) {
				sb.append(fieldError.getDefaultMessage()); //에러메세지를 합친다.
			}
			
			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		
		String email = principal.getName();
		Long borrowId;
		
		try {
			borrowId = borrowService.borrow(borrowDto, email);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Long>(borrowId , HttpStatus.OK);
		
	}
	
	
	
}
