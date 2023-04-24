package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member") // member라는 이름으로 model에 저장된 데이터는 자동으로 세션에 등록된다
@Controller // - 뷰를 리턴
public class BoardController {

	@Autowired
	private BoardService bs;

	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	// 글목록
	@GetMapping("/getBoardList") // 리턴을 안했는데 어떻게 .html로 가는건지?
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) { // 리턴 타입이 void이다. "/getBoardList" path로 요청했기 때문에 자동으로 getBoardList로 응답화면이 결정됨
											// 책 356 페이지 참고.
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		model.addAttribute("boardlist", bs.getBoardList());
		
		return "getBoardList.html";
	}

	// 상세내용 보기
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		model.addAttribute("board", bs.getBoard(board));
		
		return "getBoard.html";
	}
	
	//글 등록 화면을 보여주는 역할만 하는 메소드.
	@GetMapping("/insertBoard")
	public String insertView(@ModelAttribute("member") Member member) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		return "insertBoard";
	}
	
	// 글 작성
	@PostMapping("/insertBoard") // url은 insertBoard로 이동
	public String insertBoard(@ModelAttribute("member") Member member, Board board) { // 리턴 타입이 String이라는 뜻은 리턴한 페이지 따로 지정하겠다는 뜻.
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		bs.insertBoard(board);
		return "redirect:getBoardList";
	}

	// 글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) { //게시글 수정 버튼 눌렀을 때 다른 페이지로 이동시켜서 수정시킬 수도 있다. 그러면 
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		bs.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		
		bs.deleteBoard(board);
		return "redirect:getBoardList";
	}
}
