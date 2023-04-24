package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
		Member findMember = ms.getMember(member); // 매개변수로 아이디 비번 받아서, db에 해당하는 정보 있는지 검사하고 있으면 findMember에 저장
		
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) { // db에있는 비번과 매개변수로 받은 비번 비교
			model.addAttribute("member", findMember); // 이 라인의 역할은 무엇인가 - model객체에 findMember 객체를 속성으로 추가. 이 속성은 뷰에서 사용됨
													//스프링 MVC의 Controller는 기본적으로 Java Beans 규칙에 맞는 객체는 자동으로 화면에 전달해준다.
													// 즉 model에 저장해서 뷰에 전달?
			return "redirect:getBoardList"; // forward 하니까 에러. 포워드는 로그인 폼에서 입력된 정보를 갖고 있는데. model에 저장된 정보 한번더 ~ 하려하니까 에러
			//로그인 성공하면 getBoardList로 이동		// model 지우고 forward:~~ 하면 로그인 됨
		}else {
			return "redirect:login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index.html";
	}
}
