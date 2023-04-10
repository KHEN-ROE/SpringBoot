package edu.pnu;

import java.util.Date;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class BoardTest {

	@Autowired
	BoardRepository br;
	
	@Autowired
	MemberRepository mr;
	
	
	@Test
	public void BoardInsert() {
		for (int i = 0; i < 11; i++) {
			Board b = new Board();
			b.setContent("content"+i);
			b.setCnt(0L);
			b.setCreateDate(new Date());
			b.setTitle("title"+i);
			b.setWriter("writer"+i);
			
			br.save(b);
			
			Member m = new Member();
			m.setId("member"+i);
			m.setPassword("1234");
			m.setName("홍길동");
			m.setRole("admin");
			
			mr.save(m);
		}
	}
	
	
	
}
