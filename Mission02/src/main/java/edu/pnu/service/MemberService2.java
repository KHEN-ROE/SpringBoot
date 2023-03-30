package edu.pnu.service;



import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService2 {

	//메서드 호출 위해 객체 생성
	MemberDAO dao = new MemberDAO();
	

	//생성자
	public MemberService2() {
		
	}
	
	// 1. get 방식
	public List<MemberVO> getMember() {
		return dao.getMember();
	}
	
	// 2. get 방식2 - id로 read
	public MemberVO getMember2(int id) {
		return dao.getMember2(id);
	}
	
	// 3. post 방식
	public MemberVO addMember(MemberVO m) {
		return dao.addMember(m);
	}
	
	
	// 4. put 방식 - create
	public MemberVO updateMember(MemberVO m) {
		return dao.updateMember(m);
	}
	
	// 5. delete 방식
	public MemberVO deleteMember(int id) {
		return dao.deleteMember(id);
	}
}
