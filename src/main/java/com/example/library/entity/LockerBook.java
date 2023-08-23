package com.example.library.entity;



import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
@Entity
@Table(name="locker_book")
public class LockerBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="locker_book_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="locker_id")
	private Locker locker;
	
}
