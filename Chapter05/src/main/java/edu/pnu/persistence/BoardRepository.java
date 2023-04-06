package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.pnu.domain.Board;
	//앞에 T는 클래스명, 뒤에는 자료형
	//이 인터페이스가 Board 클래스에 대한 정보를 갖고 있다.
	//테이블마다 각각의 레포지토리가 있다
public interface BoardRepository extends CrudRepository<Board, Long> {

	List<Board> findByTitleContaining(String searchKeyword);

	List<Board> findByTitleContainingAndCntGreaterThan(String string, long l);

	List<Board> findByCntBetweenOrderBySeqAsc(long l, long m);

//	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String string, String string2, Pageable paging);
	
	Page<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String string, String string2, Pageable paging);

	
	@Query("SELECT b FROM Board b")
	List<Board> queryAnnotationTest(Pageable paging);

	
	
}
