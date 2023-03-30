package edu.pnu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	//로깅
	private static final Logger log = LoggerFactory.getLogger(MemberController2.class);
	
	//생성자
	public MemberController2() {
		System.out.println("MemberController2 생성");
		
		log.error("error message 입니다");
		log.warn("warn message 입니다");
		log.info("info message 입니다"); //info가 default임. 그래서 위에서부터 info까지만 출력됨
		log.debug("debug message 입니다");
		log.trace("trace message 입니다");
		
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
