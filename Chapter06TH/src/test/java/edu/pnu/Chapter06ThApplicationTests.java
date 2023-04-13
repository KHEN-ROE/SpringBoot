package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
class Chapter06ThApplicationTests {

	@Autowired
	BoardRepository br;
	
	
	@Test
	void contextLoads() {
	}

	@Test
	void insertDate() {
		for(int i=0; i<10; i++) {
			Board b = new Board();
			b.setTitle("제목"+i);
			b.setCnt(0L);
			b.setContent("내용"+i);
			b.setCreateDate(new Date());
			b.setWriter("작성자"+i);
			
			br.save(b);
		}
		
	}
}
