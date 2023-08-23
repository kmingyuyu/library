package com.example.library.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.library.constant.BorrowOk;

import groovy.transform.ToString;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@ToString
@Getter
@Setter
@Table(name="borrow")
public class Borrow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="borrow_id")
	private Long id;
	
	private LocalDateTime borrowDate; //대출 날짜
	
	private LocalDateTime returnDate; //반납 예정 날짜
	
	@Enumerated(EnumType.STRING)
	private BorrowOk borrowOK;  //대출 상태 (대출 , 반납 , 대출취소)
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	@OneToMany(mappedBy = "borrow" , cascade = CascadeType.ALL , 
			orphanRemoval = true , fetch = FetchType.LAZY)
	private List<BorrowBook> borrowBooks = new ArrayList<>();
	
	public void addBorrowBook(BorrowBook borrowBook) {
		this.borrowBooks.add(borrowBook);
		borrowBook.setBorrow(this);
	}
	
	
	public static Borrow createBorrow(Member member , List<BorrowBook> borrowBookList) {
		
		Borrow borrow = new Borrow();
		borrow.setMember(member);
		
		for(BorrowBook borrowBook : borrowBookList) {
			borrow.addBorrowBook(borrowBook);
		}
		
		LocalDateTime nowTime = LocalDateTime.now();
		
		borrow.setBorrowOK(BorrowOk.BORROW);
		borrow.setBorrowDate(nowTime);
		borrow.setReturnDate(nowTime.plusDays(7));
		
		return borrow;
	}

}
