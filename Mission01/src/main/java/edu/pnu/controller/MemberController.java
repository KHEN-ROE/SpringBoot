package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pnu.edu.domain.Member;
import pnu.edu.service.MemberService;

@RestController
public class MemberController {

	MemberService m1 = new MemberService();
	
	public MemberController() {
		System.out.println("MemberController 생성");
	}
	
	
	@GetMapping("/Member")
	public List<Member> getMembers(){
		
		
		
		return m1.getMember();
	}
	
	//@PathVariable은 url경로에 변수 넣어주는 것
//	@RequestMapping(value = "/Member/{id}", method = RequestMethod.GET)
	
	@GetMapping(path="/Member/{id}")
	public Member getMember2(@PathVariable int id) {
		
		return m1.getMember2(id);
		
	}
	
	//나머지는 집가서 하기
	
	
	
}
