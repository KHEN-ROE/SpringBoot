package edu.pnu.service;

import java.util.HashMap;
import java.util.Map;

import edu.pnu.dao.MemberDAOH2impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;
import edu.pnu.log.dao.LogDAOH2Impl;

public class MemberService {

	MemberInterface memberDao = new MemberDAOH2impl(); // 근데 왜 인터페이스 타입인가? 인터페이스에 사용할 메서드가 있고 
//	MemberInterface memberDao = new MemberDAOListimpl();
	LogDAOH2Impl logdao = new LogDAOH2Impl();
	
	
	
	
	//기본 생성자
	public MemberService() {
			
	}
	
	//read1
	public Map<String, Object> getMembers() {
		logdao.addLog(memberDao.getMembers());
		return memberDao.getMembers();
	}
	//read2
	public Map<String, Object> getMember(Integer id){
		logdao.addLog(memberDao.getMember(id));
		return memberDao.getMember(id);
	}
	//create
	public Map<String, Object> addMember(MemberVO member) {
		Map<String, Object> map1 = new HashMap<>();
//		memberDao.addMember(member);
		map1.put("add", memberDao.addMember(member)); // 여기서 안먹힘
		logdao.addLog(map1);
		return memberDao.addMember(member); // 두 번 호출 돼서 에러 발생. return에서 호출하지 않으면?
//		return null;
		
	}
	//update
	public Map<String, Object> updateMember(MemberVO member) {
		return memberDao.updateMember(member);
	}
	//delete
	public Map<String, Object> deleteMember(Integer id) {
		return memberDao.deleteMember(id);
	}
	
}
