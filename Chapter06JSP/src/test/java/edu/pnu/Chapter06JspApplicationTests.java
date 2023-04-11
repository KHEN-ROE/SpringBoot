package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
class Chapter06JspApplicationTests {

//	@Test
	void contextLoads() {
	}

	@Autowired
	BoardRepository br;
	
	@Test
	public void insertData() {
		for(int i=0; i<10; i++) {
			Board b = new Board();
			b.setContent("test"+i);
//			b.setCreateDate(new Date());
//			b.setCnt(0L);
			b.setTitle("제목"+i);
			b.setWriter("tester"+i);
			
			br.save(b);
		}
	}
	
}
