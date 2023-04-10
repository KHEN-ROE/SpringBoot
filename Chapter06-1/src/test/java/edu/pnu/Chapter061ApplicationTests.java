package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.DBoard;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.service.DBoardService;

@SpringBootTest
class Chapter061ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	BoardRepository br;
	
	@Autowired
	DBoardService bs;
	
//	@Test
	public void insertDataTest() {
		for(int i=0; i<11; i++) {
			br.save(DBoard.builder()
					.title("title"+i)
					.content("content"+i)
					.createDate(new Date())
					.cnt(0L)
					.build()
					);
		}
	}
	
	@Test
	public void getBoardListTest() {
		List<DBoard> list = bs.getBoards();
		for(DBoard board : list) {
			System.out.println(board);
		}
	}
	
	
	
}
