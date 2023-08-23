package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> , BookRepositoryCustom{
	
	List<Book> findByBookName(String bookName);
	
	
	
	
}
