package com.example.library.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.example.library.entity.BookImg;
import com.example.library.repository.BookImgRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookImgService {
	
	/* private String bookImgLocation = "C:/library/book"; */
	
	@Value("${bookImgLocation}")
	private String bookImgLocation;
	
	private final BookImgRepository bookImgRepository;
	
	private final FileService fileService;
	
//	이미지 저장
	public void saveBookImg(BookImg bookImg , MultipartFile bookImgFile) throws Exception {
		
		String oriImgName = bookImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile
					(bookImgLocation, oriImgName, bookImgFile.getBytes());
			
			imgUrl = "/img/book/" + imgName;
		}
		
		bookImg.updateItemImg(oriImgName, imgName, imgUrl);
		bookImgRepository.save(bookImg);
		
		
	}
	
//	도서삭제시 이미지삭제
	public void deleteBookImg(BookImg bookImg) throws Exception {
		fileService.deleteFile(bookImgLocation + "/" + bookImg.getImgName());
	}
	
	
//	이미지 업데이트
	public void updateBookImg(Long bookImgId ,MultipartFile bookImgFile ) throws Exception {
		
		if(!bookImgFile.isEmpty()) {
			
			BookImg savedBookImg = bookImgRepository.findById(bookImgId)
									.orElseThrow(EntityNotFoundException::new);
			
			if(!StringUtils.isEmpty(savedBookImg.getImgName())) {
				fileService.deleteFile(bookImgLocation + "/" + savedBookImg.getImgName());
			}
			
			String oriImgName = bookImgFile.getOriginalFilename();
			String imgName = fileService.uploadFile
					(bookImgLocation, oriImgName, bookImgFile.getBytes());
			
			String imgUrl = "/img/book/" + imgName;
			
			
			savedBookImg.updateItemImg(oriImgName, imgName, imgUrl);
			
		}
		
	}
	
	
	
}
