package com.rubypaper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient1 {

	public static void insertBoard(EntityManagerFactory emf) {

		// EntityManager - crud 기능 처리하기 위한 객체
		EntityManager em = emf.createEntityManager();
		// 트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		try {
			// 트랜잭션 시작
			tx.begin();
			
			for(int i=0; i<10; i++) {
				if(i<9) {
					Board board = new Board();
					board.setTitle("테스트 제목");
					board.setWriter("TESTER");
					board.setContent("테스트");
					board.setCreateDate(new Date());
					board.setCnt(0L);
					// 글 등록
					em.persist(board);
				}else {
					System.out.println(10/0);
				}
			
			
			}
			// 트랜잭션 commit
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// 트랜잭션 롤백
			tx.rollback();
		}
	}

	public static void main(String[] args) {

		// EntityManager 생산하는 공장
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");

		
			insertBoard(emf);
		
	}
}
