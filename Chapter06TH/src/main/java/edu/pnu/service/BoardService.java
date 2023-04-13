package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardService {

	@Autowired
	BoardRepository br;
	
//	@Autowired
//	Board board;
	
	public List<Board> getBoards(){
		return (List<Board>) br.findAll();
	}
	
//	public Board getBoard(Long seq) {
//		return br.findById(seq).get();
//	}
	
	public Board getBoard(Board b) {
		return br.findById(b.getSeq()).get();
	}
	
	public Board insertBoard(Board b) {
		 return br.save(b);
	}
	
	public Board updateBoard(Board b) {
		Board board = br.findById(b.getSeq()).get();
		board.setTitle(b.getTitle());
		board.setContent(b.getContent());
		return br.save(board);
	}
	
	public void deleteBoard(Long seq) {
		br.deleteById(seq);
	}
	
}
