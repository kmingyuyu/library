package com.example.library.entity;


import com.example.library.constant.StockOk;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@ToString
@Getter
@Setter
@Table(name="borrow_book")
public class BorrowBook extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="borrow_book_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="borrow_id")
	private Borrow borrow;
	
	public static BorrowBook createBorrowBook(Book book) {
		
		BorrowBook borrowBook = new BorrowBook();
		borrowBook.setBook(book);
		
		book.setStockOk(StockOk.N);
		book.setBorrowCount(book.getBorrowCount()+1);;
		return borrowBook;
		
	}
	
	
	
	
	
}
