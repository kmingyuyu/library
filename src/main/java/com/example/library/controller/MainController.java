package com.example.library.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.library.dto.BookSearchDto;
import com.example.library.dto.MainBookDto;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final BookService bookService;
	
	@GetMapping(value="/")
	public String main(BookSearchDto bookSearchDto , Optional<Integer> page , Model model) {
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 6);
		
		Page<MainBookDto> newBooks = bookService.getBookNewPage(bookSearchDto, pageable);
		
		Page<MainBookDto> countBooks = bookService.getBookCountPage(bookSearchDto, pageable);
		
		
		model.addAttribute("newBooks" ,  newBooks);
		model.addAttribute("countBooks" ,  countBooks);
		model.addAttribute("bookSearchDto" ,  bookSearchDto);
		
		return "main";
	}
	

	
	
	
	
}
