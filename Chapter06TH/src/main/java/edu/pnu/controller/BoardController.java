package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

//@RestController
@Controller
public class BoardController {

	@Autowired
	BoardService bs;
	
	//생성자
	public BoardController() {
		System.out.println("boardController 생성");
	}
//	
//	@GetMapping("/board")
//	public List<Board> getBoards(){
//		return bs.getBoards();
//	}
	
//	@GetMapping("/board/{seq}")
//	public Board getBoard(@PathVariable Long seq) {
//		return bs.getBoard(seq);
//	}
//	
	@GetMapping("/board")
	public String getBoard(@RequestBody Board b, Model model) { //제이슨 형태로 파라미터를 받는다.
		model.addAttribute("board", bs.getBoard(b));
		return "/board";
	}
	
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("greeting", "hello Thymeleaf !");
		return "hello";
	}
	
//	@PostMapping("/board")
//	public Board insertBoard(Board b) {
//		return bs.insertBoard(b);
//	}
//	
//	@PutMapping("/board")
//	public Board updateBaord(Board b) {
//		return bs.updateBoard(b);
//	}
//	
//	@DeleteMapping("/board/{seq}")
//	public void deleteBoard(@PathVariable Long seq) {
//		bs.deleteBoard(seq);
//	}
//	
	
	
}
