package com.example.library.service;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.library.entity.Member;
import com.example.library.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		checkNewMember(member);
		Member savedMember = memberRepository.save(member);
		
		return savedMember;
	}
	
	private void checkNewMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		Member findPhoneNumber = memberRepository.findByPhoneNumber(member.getPhoneNumber());
		
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 이메일입니다.");
		}
		
		if(findPhoneNumber != null) {
			throw new IllegalStateException("이미 가입된 번호입니다.");
		}
		
	}
	
	
	
	public void checkPhone(String phoneNumber , int randomNumber) throws CoolsmsException {
		String api_key = "NCSBN6UJEBJBMYIF";
		String api_secret = "LBPFALQKEAV0XMWOB2RNYTUMDUZIRJJU";
		
		Message coolsms = new Message(api_key, api_secret);
		
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber);
		params.put("from", "01071280150");
		params.put("type", "SMS");
		params.put("text", "[MOOK] 인증번호 "+ randomNumber +" 를 입력하세요.");
		params.put("app_version", "test app 1.2"); // application name and version
		
		 try {
		        JSONObject obj = (JSONObject) coolsms.send(params);
		        System.out.println(obj.toString());
		      } catch (CoolsmsException e) {
		        System.out.println(e.getMessage());
		        System.out.println(e.getCode());
		      }
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return User.builder()
			  .username(member.getEmail())
			  .password(member.getPassword())
			  .roles(member.getRole().toString())
			  .build();
	}

}
