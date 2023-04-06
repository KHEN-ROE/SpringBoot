package edu.pnu;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest {

	@Autowired // 객체를 만들어주겠다는 뜻
	BoardRepository br; // 원래는 인터페이스에 있는 메서드를 구현해야하는데, 스프링부트 라이브러리가 컴파일을 할 때 메서드를 구현한 클래스를 만들어서 실행시켜준다
	
	//인터페이스를 Autowired하면 이 인터페이스를 구현한 모든 클래스를 검색하고 그 클래스들의 객체를 스프링부트가 만들어준다. 따라서 클래스들에 있는 모든 메서드를 가져다 쓸 수 있다.
	//단순히 인터페이스를 구현하는 것과는 다르다. 인터페이스를 구현하는 것은 인터페이스에 있는 메서드를 필수적으로 오버라이딩해하는 반면에,
	//인터페이스를 autowired 하면 인터페이스를 구현한 모든 클래스의 인스턴스를 제공받을 수 있다.
	
	// 데이터 100개 입력
//	@Test
	public void BoardInsert() {

		Random random = new Random();

		for (int i = 0; i < 100; i++) {
			Board board = new Board();
			board.setTitle("title" + i);
			board.setContent("content" + i);
			board.setWriter("writer" + i);
			board.setCreateDate(new Date());
			board.setCnt(random.nextLong(100));

			br.save(board);
		}
	}

	// title에 '1' 포함된 데이터 출력
//	@Test
	public void findBy1() {
		List<Board> list = br.findByTitleContaining("1");

		System.out.println("검색결과");
		for (Board b : list) {
			System.out.println("---> " + b.toString());
		}
	}

	// title에 '1' 포함, cnt가 50 이상인 데이터 출력
//	@Test
	public void findBy2() {
		List<Board> list = br.findByTitleContainingAndCntGreaterThan("1", 50L);

		System.out.println("검색결과");
		for (Board b : list) {
			System.out.println("---> " + b.toString());
		}
	}

	// cnt가 10~50 사이인 데이터를 seq오름차순으로 출력
//	@Test
	public void findBy3() {
		List<Board> list = br.findByCntBetweenOrderBySeqAsc(10L, 50L);

		System.out.println("검색결과");
		for (Board b : list) {
			System.out.println("---> " + b.toString());
		}
	}

	// title에 "10"이 포함되거나 content에 "2"가 포함되는 데이터를 seq 내림차순으로 출력
	@Test
	public void findBy4() {
		Pageable paging = PageRequest.of(0, 5);
//		List<Board> list = br.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2", paging);
		Page<Board> pageInfo = br.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2", paging);
		
		System.out.println("page size : " + pageInfo.getSize());
		System.out.println("total pages : " + pageInfo.getTotalPages());
		System.out.println("total count : " + pageInfo.getTotalElements());
		System.out.println("next : " + pageInfo.nextPageable());
		
		List<Board> boardList = pageInfo.getContent();
		
//		System.out.println("검색결과");
//		for (Board b : list) {
//			System.out.println("---> " + b.toString());
//		}
		System.out.println("검색결과");
		for (Board b : boardList) {
			System.out.println("---> " + b.toString());
		}
	}
	
//	@Test
	public void pagingTest() {
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq"); //PageRequest 클래스의 스태틱 메서드 of 호출
		List<Board> list = br.queryAnnotationTest(paging);
		
		System.out.println("검색결과");
		for(Board b : list) {
			System.out.println(b.toString());
		}
	}
	
	
}
