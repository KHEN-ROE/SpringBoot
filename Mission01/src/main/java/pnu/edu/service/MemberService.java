package pnu.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import pnu.edu.domain.Member;

//service는 로직 구현.
@Service
public class MemberService {

	
	List<Member> list = new ArrayList<>();
	Member m = new Member();
	
	public MemberService() {
		list.add(new Member(1, "1234", "홍길동", new Date()));
		list.add(new Member(2, "1235", "홍길동1", new Date()));
		list.add(new Member(3, "1236", "홍길동2", new Date()));
		list.add(new Member(4, "1237", "홍길동3", new Date()));
		list.add(new Member(5, "1238", "홍길동4", new Date()));
	
			
	}
	
	public List<Member> getMember(){
		
		
		return list;
	}
	
	
	public Member getMember2(int id) {
		
		for(Member m : list) {
			if(m.getId()==id) {
				return m;
			}
		}
		
		return null;
		
		
		
	}
	
	
}
