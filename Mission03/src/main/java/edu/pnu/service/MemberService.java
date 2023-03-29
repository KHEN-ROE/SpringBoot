package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDAOH2impl;
import edu.pnu.dao.MemberDAOListimpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {

	MemberInterface memberDao = new MemberDAOH2impl(); // 근데 왜 인터페이스 타입인가? 인터페이스에 사용할 메서드가 있고 
//	MemberInterface memberDao = new MemberDAOListimpl();
	
	//기본 생성자
	public MemberService() {
			
	}
	
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
	}
	
	public MemberVO getMember(Integer id){
		return memberDao.getMember(id);
	}
	
	public MemberVO addMember(MemberVO member) {
		return memberDao.addMember(member);
	}
	
	public MemberVO updateMember(MemberVO member) {
		return memberDao.updateMember(member);
	}
	
	public MemberVO deleteMember(Integer id) {
		return memberDao.deleteMember(id);
	}
	
}
