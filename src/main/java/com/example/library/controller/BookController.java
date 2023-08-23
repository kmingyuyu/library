package com.example.library.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.library.constant.TypeOk;
import com.example.library.dto.BookFormDto;
import com.example.library.dto.BookSearchDto;
import com.example.library.dto.MainBookDto;
import com.example.library.entity.Book;
import com.example.library.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
//	도서 등록 페이지
	@GetMapping(value="/admin/book/new")
	public String bookForm(Model model) {
		
		BookFormDto bookFormDto = new BookFormDto();
		
		model.addAttribute("bookFormDto" , bookFormDto);
		
		return "admin/reg/bookForm";
	}
	
//	도서 등록 기능
	@PostMapping(value="/admin/book/new")
	public String bookNew
	(@Valid BookFormDto bookFormDto , BindingResult bindingResult , 
			Model model , @RequestParam("bookImgFile") List<MultipartFile> bookImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "admin/reg/bookForm";
		}
		
		if(bookImgFileList.get(0).isEmpty()) {
			model.addAttribute("errorMessage", "책의 메인 이미지는 필수입니다.");
			return "admin/reg/bookForm";
		}
		if(bookImgFileList.get(1).isEmpty()) {
			model.addAttribute("errorMessage", "책의 사이드 이미지는 필수입니다.");
			return "admin/reg/bookForm";
		}
		if(bookImgFileList.get(2).isEmpty()) {
			model.addAttribute("errorMessage", "책의 뒷면 이미지는 필수입니다.");
			return "admin/reg/bookForm";
		}
		
		try {
			bookService.saveBook(bookFormDto, bookImgFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage" , "상품 등록 중 에러가 발생했습니다");
			return "admin/reg/bookForm";
		}
		
		
		return "redirect:/";
	}
	

	
	
//	도서관리 페이지(전체) (
	@GetMapping(value = {"/admin/books" , "/admin/books/{page}"})
	public String bookManage_total(BookSearchDto bookSearchDto ,  @PathVariable("page") Optional<Integer> page , Model model) {
		
		
		Pageable pageable = PageRequest.of(page.isPresent() ?  page.get() : 0 , 10);
		
		Page<Book> books = bookService.getAdminBookPage(bookSearchDto, pageable);
		
		
		model.addAttribute("books" , books);
		model.addAttribute("bookSearchDto" , bookSearchDto);
		model.addAttribute("maxPage" , 5);
		
		return "admin/mng/bookMng";
	}
	
	
//	수정 페이지
	@GetMapping(value="admin/book/update/{bookId}") 
	public String bookModify(Model model , @PathVariable("bookId") Long bookId) {
		  
		try {
			BookFormDto bookFormDto = bookService.getBookDetail(bookId);
			
			model.addAttribute("bookFormDto" , bookFormDto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage" , "정보 가져올때 에러가 발생 하였습니다");
			model.addAttribute("bookFormDto" , new BookFormDto());
		}
		  
		return "admin/reg/bookModifyForm";
		  
	  }
	 
//	도서 수정 기능
	@PostMapping(value="/admin/book/update/{bookId}")
	public String bookUpdate
	(@Valid BookFormDto bookFormDto , BindingResult bindingResult ,
			Model model , @RequestParam("bookImgFile") List<MultipartFile> bookImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "admin/reg/bookModifyForm";
		}
		
		if(bookImgFileList.get(0).isEmpty() && bookFormDto.getId() == null) {
			model.addAttribute("errorMessage", "책의 메인 이미지는 필수입니다.");
			return "admin/reg/bookModifyForm";
		}
		
		try {
			bookService.updateBook(bookFormDto, bookImgFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage" , "상품 등록 중 에러가 발생했습니다");
			return "admin/reg/bookModifyForm";
		}
		
		return "redirect:/";
	}
	
//	도서 삭제 기능
	@DeleteMapping("/delete/{bookId}")
	public String bookDelete(@PathVariable("bookId") Long bookId) {
		
		System.out.println("id = " + bookId );
		
		try {
			bookService.deleteBook(bookId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
		
	}
	
//	상세페이지
	@GetMapping(value = "/book/{bookId}")
	public String bookDetail(Model model , @PathVariable("bookId") Long bookId){
		
		BookFormDto bookFormDto = bookService.getBookDetail(bookId);
		model.addAttribute("book" , bookFormDto);
		
		return "member/book/bookDetail";
		
	}
	
//	도서 전체페이지 ( 신착도서 순 )
	@GetMapping
	(value = {"/book/totalNew" , "/book/totalNew/{page}", 
			   })
	public String bookTotalNew(BookSearchDto bookSearchDto , Model model ,
			@PathVariable("page") Optional<Integer> page
			) {
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 12);
		Page<MainBookDto> books = bookService.getBookNewPage(bookSearchDto, pageable);
		
		model.addAttribute("books" ,  books);
		model.addAttribute("bookSearchDto" ,  bookSearchDto);
		model.addAttribute("maxPage" , 5);
		
		return "member/book/bookTotalNew";
	}
	
	@GetMapping
	(value = {"/book/totalCount" , "/book/totalCount/{page}", 
			   })
	public String bookTotalCount(BookSearchDto bookSearchDto , Model model ,
			@PathVariable("page") Optional<Integer> page
			) {
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 12);
		Page<MainBookDto> books = bookService.getBookCountPage(bookSearchDto, pageable);
		
		model.addAttribute("books" ,  books);
		model.addAttribute("bookSearchDto" ,  bookSearchDto);
		model.addAttribute("maxPage" , 5);
		
		return "member/book/bookTotalCount";
	}
	
	
	
	
	
}
