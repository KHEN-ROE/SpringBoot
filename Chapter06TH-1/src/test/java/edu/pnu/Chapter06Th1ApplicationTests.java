package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
class Chapter06Th1ApplicationTests {

	@Autowired
	BoardRepository br;
	
	@Autowired
	MemberRepository mr;
	
	@Test
	void contextLoads() {
	}

	@Test
	void insertDate() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setName("둘리");
		member1.setPassword("1234");
		member1.setRole("관리자1");
		mr.save(member1);
		
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setName("도우너");
		member2.setPassword("4321");
		member2.setRole("관리자2");
		mr.save(member2);
		
		
		for(int i =0; i<5; i++) {
			Board b = new Board();
			b.setWriter("둘리");
			b.setTitle("둘리가 쓴 제목"+i);
			b.setContent("둘리가 쓴 내용"+i);
			br.save(b);
		}
		
		for(int i =0; i<5; i++) {
			Board b = new Board();
			b.setWriter("도우너");
			b.setTitle("도우너가 쓴 제목"+i);
			b.setContent("도우너가 쓴 내용"+i);
			br.save(b);
		}
		
		
		
	}
	
}
