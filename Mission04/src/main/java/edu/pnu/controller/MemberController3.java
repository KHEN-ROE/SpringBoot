package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController3 {
	
	MemberService ms = new MemberService();
	
	//생성자
	public MemberController3() {
		System.out.println("MemberController3 생성");
	}
	
	//get 방식
	@GetMapping("/member3")
	public List<MemberVO> getMembers() {
		return ms.getMembers();
	}
	
	//get 방식2
	@GetMapping("/member3/{id}")
	public MemberVO getMember(@PathVariable int id){
		return ms.getMember(id);
	}
	
	//post 방식
	@PostMapping("/member3")
	public MemberVO addMember(MemberVO member) {
		return ms.addMember(member);
	}
	
	//put 방식
	@PutMapping("/member3")
	public MemberVO updateMember(MemberVO member) {
		return ms.updateMember(member);
	}
	
	//delete 방식
	@DeleteMapping("/member3/{id}")
	public MemberVO deleteMember(@PathVariable Integer id) {
		return ms.deleteMember(id);
	}
}
