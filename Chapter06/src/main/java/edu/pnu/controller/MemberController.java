package edu.pnu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService ms;
	
	//생성자
	public MemberController() {
		System.out.println("MemberController 생성");
	}
	
	//get방식
	@GetMapping("/member")
	public List<Member> getMembers(){
		return ms.getMembers();
	}
	
	//get방식2
	@GetMapping("/member/{id}")
	public Member getMember(@PathVariable String id) {
		return ms.getMember(id);
	}
	
	
}
