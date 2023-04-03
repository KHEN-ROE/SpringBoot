package edu.pnu.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAOH2impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;
import edu.pnu.log.dao.LogDAO;
import edu.pnu.log.dao.LogDAOH2Impl;

@Service
public class MemberService {

	@Autowired
	MemberInterface memberDao = new MemberDAOH2impl(); // 근데 왜 인터페이스 타입인가? 인터페이스에 사용할 메서드가 있고 
//	MemberInterface memberDao = new MemberDAOListimpl();
	@Autowired
	LogDAO logdao = new LogDAOH2Impl();

	//기본 생성자
	public MemberService() {
			
	}
	
	//read1
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMembers() {
		return logdao.addLog(memberDao.getMembers());
	}
	//read2
	public Map<String, Object> getMember(Integer id){
		return logdao.addLog(memberDao.getMember(id)); 
	}
	//create
	public Map<String, Object> addMember(MemberVO member) {
		return logdao.addLog(memberDao.addMember(member)); //두 번 부르면 에러나서 이렇게 함.
	}
	//update
	public Map<String, Object> updateMember(MemberVO member) {
		return logdao.addLog(memberDao.updateMember(member));
	}
	//delete
	public Map<String, Object> deleteMember(Integer id) {
		return logdao.addLog(memberDao.deleteMember(id));

	}
	
}
