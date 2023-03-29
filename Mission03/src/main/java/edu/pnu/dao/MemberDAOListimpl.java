package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDAOListimpl implements MemberInterface {

	List<MemberVO> list = new ArrayList<>();
	MemberVO mv;
	
	//기본 생성자
	public MemberDAOListimpl() {
		for(int i=1; i<=5; i++) {
			list.add(new MemberVO(i, i+"234", "홍길동"+i, new Date()));
		}
	}
	
	@Override
	public List<MemberVO> getMembers() {
		return list;
	}

	@Override
	public MemberVO getMember(Integer id) {
		for(MemberVO m: list) {
			if(m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	@Override
	public MemberVO addMember(MemberVO member) {
		int id = member.getId();
		String pass = member.getPass();
		String name = member.getName();
		Date regidate = new Date();
		mv = new MemberVO(id, pass, name, regidate);
		list.add(mv);
		return mv;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		for(MemberVO m : list) {
			if(m.getId() == member.getId()) {
				m.setPass(member.getPass());
				m.setName(member.getName());
				return m;
			}
		}
		return null;
	}

	@Override
	public MemberVO deleteMember(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id) {
				list.remove(m.getId());
				return m;
			}
		}
		return null;
	}

}
