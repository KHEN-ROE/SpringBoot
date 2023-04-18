package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardRepository br;
	
	@Override
	public List<Board> getBoardList(){
		return (List<Board>) br.findAll();
	}
	
	@Override
	public Board getBoard(Board board) {
		return br.findById(board.getSeq()).get();
	}
	
	@Override
	public void insertBoard(Board board) {
		br.save(board);
	}

	@Override
	public void updateBoard(Board board) {
		Board findBoard = br.findById(board.getSeq()).get();
		findBoard.setContent(board.getContent());
		findBoard.setTitle(board.getTitle());
		br.save(findBoard);
		
	}

	@Override
	public void deleteBoard(Board board) {
		br.deleteById(board.getSeq());
	}
	
}
