package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.constant.ImgChoiceOk;
import com.example.library.entity.BookImg;

public interface BookImgRepository extends JpaRepository<BookImg , Long> {
	
	List<BookImg> findByBookId(Long bookId);
	
	List<BookImg> findByBookIdOrderByIdAsc(Long bookId);
	
	BookImg findByBookIdAndImgChoiceOk(Long bookId , ImgChoiceOk imgChoiceOk);
	
}
