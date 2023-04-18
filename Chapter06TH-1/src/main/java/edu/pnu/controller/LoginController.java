package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@SessionAttributes("member")
@Controller
public class LoginController {
	
	@Autowired
	private MemberService ms;
	
	@GetMapping("/login")
	public void loginView() {
	}
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = ms.getMember(member);
		
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);
			return "redirect:getBoardList"; // forward 하니까 에러. 포워드는 로그인 폼에서 입력된 정보를 갖고 있는데. model에 저장된 정보 한번더 ~ 하려하니까 에러
											// model 지우고 forward:~~ 하면 로그인 됨
		}else {
			return "redirect:login";
		}
	}
}
