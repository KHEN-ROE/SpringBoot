package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

//controller 쓰면 restAPI 제공 안 함.
//@RestController
@Controller
public class BoardController { //controller 방식은 앞에 한 mission들에서 뷰만 붙은 거임

	@Autowired
	private BoardService boardService;
	
	// requestMapping은 crud방식 다 받음( value, method 명시 안하면)
	// @RequestMapping(value="/getBoardList", method=RequestMethod.GET)
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		
		List<Board> boardList = boardService.getBoardList();
		
//		List<Board> boardlist = new ArrayList<>();
//		for (long i = 1; i <= 10L; i++) {
//			Board board = new Board();
//			board.setSeq(i);
//			board.setTitle("게시판 프로그램 테스트");
//			board.setWriter("도우너");
//			board.setContent("게시판 프로그램 테스트입니다...");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			boardlist.add(board);
//		}
		model.addAttribute("boardList", boardList);
		return "/WEB-INF/board/getBoardList.jsp"; //뷰 이름 리턴. application properties에 접두, 접미어 설정해서 .jsp 등 생략가능
	}
	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "/WEB-INF/board/insertBoard.jsp";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", boardService.getBoard(board));
		return "/WEB-INF/board/getBoard.jsp";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "forward:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
}
