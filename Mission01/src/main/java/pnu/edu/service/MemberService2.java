package pnu.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import pnu.edu.domain.Member;

@Service
public class MemberService2 {

	List<Member> list = new ArrayList<>();
	
	//생성자
	public MemberService2() {
		
		list.add(new Member(1, "1234", "홍길동", new Date()));
		list.add(new Member(2, "1234", "홍길동", new Date()));
		list.add(new Member(3, "1234", "홍길동", new Date()));
		list.add(new Member(4, "1234", "홍길동", new Date()));
		list.add(new Member(5, "1234", "홍길동", new Date()));
	}
	
	public List<Member> getMember1(){
		
		return list;
	}
	
	public Member getMember2(int id) {
		
		for(Member m : list) {
			if(m.getId() == id) {
				return m;
			}
		}
		
		return null;
	}
	
}
