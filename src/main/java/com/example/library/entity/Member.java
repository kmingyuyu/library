package com.example.library.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.library.constant.Role;
import com.example.library.dto.MemberDto;

import groovy.transform.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ToString
@Table(name="member")
public class Member {
	
	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String email; //이메일 (아이디로 씀)
	
	@Column(nullable = false)
	private String password; //비밀번호
	
	@Column(nullable = false)
	private String phoneNumber; //핸드폰 번호
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String birth;
	
	@Enumerated(EnumType.STRING)
	private Role role; // 역활 (관리자 , 사용자)
	
	public static Member createMember(MemberDto memberDto , PasswordEncoder passwordEncoder) {
		
		String password = passwordEncoder.encode(memberDto.getPassword());
		
		Member member = new Member();
		
		member.setEmail(memberDto.getEmail());
		member.setPassword(password);
		member.setPhoneNumber(memberDto.getPhoneNumber());
		member.setName(memberDto.getName());
		member.setBirth(memberDto.getBirth());
//		member.setRole(Role.USER);
		member.setRole(Role.ADMIN);
		
		return member;
		
	}
	
	
	
}
