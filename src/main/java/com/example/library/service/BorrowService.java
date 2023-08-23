package com.example.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.dto.BorrowDto;
import com.example.library.entity.Book;
import com.example.library.entity.Borrow;
import com.example.library.entity.BorrowBook;
import com.example.library.entity.Member;
import com.example.library.repository.BookImgRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRepository;
import com.example.library.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowService {
	
	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final BorrowRepository borrowRepository;
	private final BookImgRepository bookImgRepository;
	
	
	public Long borrow(BorrowDto borrowDto , String email) {
		
		Book book = bookRepository.findById(borrowDto.getBookId())
					.orElseThrow(EntityNotFoundException::new);
		
		Member member = memberRepository.findByEmail(email);
		
		List<BorrowBook> borrowBookList = new ArrayList<>();
		
		BorrowBook borrowBook = BorrowBook.createBorrowBook(book);
		borrowBookList.add(borrowBook);
		
		Borrow borrow = Borrow.createBorrow(member, borrowBookList);
		borrowRepository.save(borrow);
		
		return borrow.getId();		
	}
	
	
	
}
