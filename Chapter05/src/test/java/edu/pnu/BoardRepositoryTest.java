package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository boardRepo;

	@Test
	public void BoardInsertTest() {

		for (int i = 0; i < 11; i++) {
			Board b = new Board(); // for문 안에 넣어야하는 이유? 각각이 다른 레코드이기 때문. 그래서 객체를 계속 새로 만들어야 함.
			b.setTitle("title"+i);
			b.setWriter("writer");
			b.setContent("content");
			b.setCreateDate(new Date());
			b.setCnt(0L);
			
			boardRepo.save(b);
		}
	}

	@Test
	public void testGetBoard() {
		Board board = boardRepo.findById(2L).get(); //board에다가 저장하는 이유? 밑에서 toString 메소드 쓰려고?
		System.out.println(board.toString());
	}
	
	@Test
	public void testUpdateBoard() {
		System.out.println("=== 2번 게시글 조회 ===");
		Board board = boardRepo.findById(2L).get(); //
		
		System.out.println("=== 2번 게시글 제목 수정 ===");
		board.setTitle("제목을 수정했습니다.");
		boardRepo.save(board);
	}
	
	@Test
	public void testDeleteBoard() {
		boardRepo.deleteById(1L);
	}

}
