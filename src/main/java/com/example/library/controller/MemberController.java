package com.example.library.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.library.dto.MemberDto;
import com.example.library.entity.Member;
import com.example.library.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
//	로그인 화면
	@GetMapping(value="/member/login")
	public String login() {
		return "member/new/login";
	}
	
//	로그인 화면
	@GetMapping(value="/member/login_test")
	public String login_test() {
		return "member/new/loginForm";
	}
	
	
	
//	회원가입 화면
	@GetMapping(value="/member/new")
	public String member(Model model) {
		model.addAttribute("memberDto" , new MemberDto());
		return "member/new/memberNew";
	}
	
	
	
	
	
//	회원가입 진행
	@PostMapping(value="/member/new")
	public String memberOk(@Valid MemberDto memberDto , BindingResult bindingResult , Model model) {
		
		if(bindingResult.hasErrors()) {
			return "member/new/memberNew";
		}
		
		try {
			Member member = Member.createMember(memberDto, passwordEncoder);
			
			memberService.saveMember(member);
		} catch (Exception e) {
			model.addAttribute("errorMessage" , e.getMessage());
			return "member/new/memberNew";
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping(value = "/phoneCheck")
	@ResponseBody
	public String numberCh(@RequestParam(value="phoneNumber")String phoneNumber , Model model) throws CoolsmsException {
		
		int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성
		
		memberService.checkPhone(phoneNumber , randomNumber);
		
		return  Integer.toString(randomNumber);
	}
	
	
	
	
	
	
	
	
}
