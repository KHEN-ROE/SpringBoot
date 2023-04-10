package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.pnu.domain.DBoard;
import edu.pnu.service.DBoardService;

@Controller //
public class BoardController {

	@Autowired
	DBoardService ds;

	@GetMapping("/board")
//	public List<DBoard> getBoards(Model model){
	public void getBoards(Model model) {
		List<DBoard> list = ds.getBoards();
		model.addAttribute("boardlist", list);
	}

//	@GetMapping("/board")
//	public List<DBoard> getBoard(Long seq) {
//		if (seq == null) {
//			System.out.println("getBoard : All");
//			return ds.getBoards();
//		}
//		
//		System.out.println("getBoard : " +seq);
//		List<DBoard> list = new ArrayList<>();
//		list.add(ds.getBoard(seq));
//		return list;
//	}

//	@GetMapping("/board/{seq}")
//	public DBoard getBoard(@PathVariable Long seq) {
//		return ds.getBoard(seq);
//	}

}
