package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pnu.edu.domain.Member;
import pnu.edu.service.MemberService2;

@RestController
public class MemberController2 {
	
	MemberService2 m2 = new MemberService2();
	
	//생성자
	public MemberController2() {
		System.out.println("MemberController2 생성");
	}
	
	//getMember1() 함수 호출
	@GetMapping("/member1")
	public List<Member> getMember1(){
		return m2.getMember1();
	}
	
	@GetMapping(path="/member1/{id}")
	public Member getMember2(@PathVariable int id) {
		return m2.getMember2(id);
	}
}
