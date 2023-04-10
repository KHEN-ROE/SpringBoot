package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;
import jakarta.websocket.server.PathParam;

@RestController
public class BoardController {

	@Autowired
	BoardService bs;
	
	//생성자
	public BoardController(){
		System.out.println("BoardController 생성");
		
	}
	
	
	
	
	//get 방식
	@GetMapping("/board")
	public List<Board> getBoards(){
		return bs.getBoards();
	}
	
	//get 방식2
	@GetMapping("/board/{seq}")
	public Board getBoard(@PathVariable Long seq) {
		return bs.getBoard(seq);
	}
	
}
