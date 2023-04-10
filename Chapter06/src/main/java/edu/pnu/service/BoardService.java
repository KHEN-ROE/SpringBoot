package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardService {

	@Autowired
	BoardRepository br;

	

	public List<Board> getBoards() {
		return (List<Board>) br.findAll();
	}

	public Board getBoard(Long seq) {
		return br.findById(seq).get();
	}

}
