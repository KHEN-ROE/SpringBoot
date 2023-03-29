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
	
	//생성자 - 객체 생성될 때마다 데이터 리스트에 삽입
	public MemberService() {
		for(int i=1; i<=5; i++) {
			list.add(new Member(i,"1234","hong",new Date()));
		}
	}
	
	// 리스트에 저장된 데이터 반환하는 함수
	public List<Member> getMember1(){
		return list;
	}
	
	// url에 입력된 변수가 list에 저장된 데이터와 일치하면 리턴
	public Member getMember2(int id) {
		
		for(Member m : list) {
			if(m.getId()==id) {
				return m;
			}
		}
		
		return null;
	}
	
	// create
	public Member addMember(Member m) {
		
		int id = m.getId();
		String pass = m.getPass();
		String name = m.getName();
		Date d = new Date();
		m = new Member(id, pass, name, d);
		list.add(m);
		return m;
	}
	
	//update
	public Member updateMember(Member m) {
		
		for(Member m1 : list) {
			if(m1.getId() == m.getId()) {
				m1.setPass(m.getPass());
				m1.setName(m.getName());
				return m1;
			}
		}
		
		return null;
	}
	
	//delete
	public Member deleteMember(Integer id) {
		for(Member m1 : list) {
			if(m1.getId()== id) {
				list.remove(m1.getId());
				return m1;
			}
		}
		
		return null;
	}
	
}
