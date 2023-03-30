package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import pnu.edu.domain.Member;
import pnu.edu.service.MemberService;

@RestController
public class MemberController {

	MemberService m1 = new MemberService();
	
	public MemberController() {
		System.out.println("MemberController 생성");
	}
	
	// 1. get 방식. service에서 데이터 저장하고 여기서 호출. -- read 영역
	@GetMapping("/Member")
	public List<Member> getMember1(){
		return m1.getMember1();
	}
	
	// 2. url에 변수 입력 방식 -- read 영역
	//@PathVariable은 url경로에 변수 넣어주는 것
	@GetMapping("/Member/{id}")
	public Member getMember2(@PathVariable int id) {
		return m1.getMember2(id);
		
	}
	
	// 3. 폼에서 입력 받으면 데이터 들어옴 -- create 영역
	@PostMapping("/Member")
	public Member addMember(Member m) {
		System.out.println("폼에서 입력받은 값 : " + m1.toString());
		return m1.addMember(m);
	}

	// 4. put -- update 영역
	@PutMapping("/Member")
	public Member updateMember(Member m) {
		System.out.println("member");
		
		return m1.updateMember(m);
	}
	
	//근데 이렇게 하면 2번이랑 겹치지 않나?. 그게 아니고, 요청 방식에 따라 다르게 맵핑 되는 거임. delete로 요청하면 됨
	// 5. delete
	@DeleteMapping("/Member/{id}")
	public Member deleteMember(@PathVariable int id) {
		System.out.println("삭제");
		
		return m1.deleteMember(id);
	}
	
	
	
	
}
