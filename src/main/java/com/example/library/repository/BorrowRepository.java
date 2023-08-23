package com.example.library.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.library.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
	
	@Query("select o from Borrow o where o.member.email = :email order by o.borrowDate desc")
	List<Borrow> findBorrow(@Param("email") String email , Pageable pageable);
	
	
	@Query("select count(o) from Borrow o where o.member.email = :email")
	Long countBorrow(@Param("email") String email);
	
}
