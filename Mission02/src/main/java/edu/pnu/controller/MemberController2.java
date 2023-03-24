package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService2;

@RestController
public class MemberController2 {
	
	//객체 생성(메소드 호출하려고)
	MemberService2 ms = new MemberService2();
	
	
	//생성자
	public MemberController2() {
		System.out.println("MemberController2 생성");
	}
	
	// 1. get 방식
	@GetMapping("/member1")
	public List<MemberVO> getMember() {
		return ms.getMember();
	}

	// 2. get 인데 id로 호출 (read)
	@GetMapping("/member1/{id}")
	public MemberVO getMember2(@PathVariable int id) {
		return ms.getMember2(id);
	}
	
	// 3. post 방식 (create)
	@PostMapping("/member1")
	public MemberVO addMember(MemberVO m) {
		return ms.addMember(m);
	}
	
	// 4. put 방식 (update)
	@PutMapping("/member1")
	public MemberVO updateMember(MemberVO m) {
		return ms.updateMember(m);
	}
	
	// 5. delete 방식
	@DeleteMapping("/member1/{id}")
	public MemberVO deleteMember(@PathVariable int id) {
		return ms.deleteMember(id);
	}
}
