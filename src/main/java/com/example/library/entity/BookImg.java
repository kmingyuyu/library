package com.example.library.entity;


import com.example.library.constant.ImgChoiceOk;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@Entity
@Table(name="book_img")
public class BookImg  {
	
	@Id
	@Column(name="book_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String oriImgName; //원본 이미지 이름
	
	private String imgName; //이미지 이름
	
	private String imgUrl; //이미지 경로
	
	@Enumerated(EnumType.STRING)
	private ImgChoiceOk imgChoiceOk; //이미지 위치 여부 (메인 사이드 뒷면 기타)
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="book_id")
	private Book book;
	
//	이미지에 대한 정보를 업데이트
	public void updateItemImg(String oriImgName , String imgName , String imgUrl) {
		this.oriImgName = oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
	}
	
}
