package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository mr;
	
	public List<Member> getMembers(){
		return (List<Member>) mr.findAll();
	}
	
	public Member getMember(String id) {
		return mr.findById(id).get();
	}
	
	
	
}
