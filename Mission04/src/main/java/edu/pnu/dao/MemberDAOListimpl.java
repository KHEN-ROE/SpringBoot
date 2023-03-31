package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDAOListimpl implements MemberInterface {

	List<MemberVO> list = new ArrayList<>();
	Map<String, Object> map = new HashMap<>();
	MemberVO mv;
	
	//기본 생성자
	public MemberDAOListimpl() {
		for(int i=1; i<=5; i++) {
			list.add(new MemberVO(i, i+"234", "홍길동"+i, new Date()));
			
		}
		map.put("data", list);
		map.put("method", "GET");
	}
	
	@Override
	public Map<String, Object> getMembers() {
		return map;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		for(MemberVO m: list) {
			if(m.getId() == id) {
				return (Map<String, Object>) map.put("m", m);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object>addMember(MemberVO member) {
		int id = member.getId();
		String pass = member.getPass();
		String name = member.getName();
		Date regidate = new Date();
		mv = new MemberVO(id, pass, name, regidate);
		list.add(mv);
		return (Map<String, Object>) map.put("list", list);
	}

	@Override
	public Map<String, Object>updateMember(MemberVO member) {
		for(MemberVO m : list) {
			if(m.getId() == member.getId()) {
				m.setPass(member.getPass());
				m.setName(member.getName());
				return (Map<String, Object>) map.put("m", m);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id) {
				list.remove(m.getId());
				return (Map<String, Object>) map.put("m", m);
			}
		}
		return null;
	}

}
