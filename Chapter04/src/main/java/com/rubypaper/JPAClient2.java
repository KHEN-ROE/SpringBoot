package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient2 { //mysql에 테스트

	public static void insertBoard(EntityManagerFactory emf) {

		// EntityManager - crud 기능 처리하기 위한 객체
		EntityManager em = emf.createEntityManager();
		// 트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		try {
			// 트랜잭션 시작
			tx.begin();

			for (int i = 0; i < 10; i++) {
				if (i < 9) {
					Board board = new Board();
					board.setTitle("테스트 제목");
					board.setWriter("TESTER");
					board.setContent("테스트");
					board.setCreateDate(new Date());
					board.setCnt(0L);
					// 글 등록
					em.persist(board);
				} else {
//					System.out.println(10 / 0); // 롤백 테스트
				}
			}
			// 트랜잭션 commit
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// 트랜잭션 롤백
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public static void findBoardOne(EntityManagerFactory emf, Long seq) {

		EntityManager em = emf.createEntityManager();
		try {
			// 글 상세 조회
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("--->" + searchBoard.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void findBoardManyJPAQuery(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		try {
			// 글 목록 조회
			String jpql = "select b from Board b order by b.seq desc";
			List<Board> boardList = em.createQuery(jpql, Board.class).getResultList();
//			TypedQuery<Board> 이렇게 시작해도 됨
			for (Board brd : boardList) {
				System.out.println("---> " + brd.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void findBoardManyNativeQuery(EntityManagerFactory emf) {

		System.out.println("findBoardManyNativeQuery");
		EntityManager em = emf.createEntityManager();

		// 방법 3-1
		List<?> list = em.createNativeQuery("select * from BOARD", Board.class).getResultList();
		for (Object o : list) {
			System.out.println(o);
		}
		System.out.println("=".repeat(60));

		// 방법 3-2
		@SuppressWarnings("unchecked")
		List<Object[]> list2 = em.createNativeQuery("select * from Board").getResultList();
		for (Object[] b : list2) {
			for (int i = 0; i < b.length; i++) {
				if (i != 0)
					System.out.println(",");
				System.out.println(b[i]);
			}
			System.out.println();
		}

		System.out.println("=".repeat(60));

		em.close();

	}

	public static void updateBoard(EntityManagerFactory emf, Long seq) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			// 트랜잭션 시작
			tx.begin();

			// 수정할 게시글 조회
			Board board = em.find(Board.class, 1L);
			board.setTitle("검색한 게시글의 제목 수정");

			// 트랜잭션 커밋
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public static void deleteBoard(EntityManagerFactory emf, Long seq) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			// 트랜잭션 시작
			tx.begin();

			// 삭제할 게시글 조회
			Board board = em.find(Board.class, 2L);
			board.setSeq(2L);

			// 게시글 삭제
			board.setSeq(2L);
			em.remove(board);

			// 트랜잭션 커밋
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// 트랜잭션 롤백
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");

		// 데이터입력
		insertBoard(emf);
		// id가 1인 데이터 검색
		findBoardOne(emf, 1l);
		// 입력된 전체 데이터 출력(JPA Query)
		findBoardManyJPAQuery(emf);
		// 입력된 전체 데이터 출력(Navtive Query)
		findBoardManyNativeQuery(emf);

		// id가 1인 데이터 수정
		updateBoard(emf, 1L);
		// 수정된 정보 확인
		findBoardOne(emf, 1l);
		// id가 2인 데이터 삭제
		deleteBoard(emf, 2L);
		// 삭제 결과 확인
		findBoardManyJPAQuery(emf);

		emf.close();

	}

}
