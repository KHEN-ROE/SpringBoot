package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@SpringBootTest
class Mission02ApplicationTests {

//	@Autowired
//	MemberDAO dao;
	
	//@Test
	void contextLoads() {
		System.out.println("이것은 테스트입니다.");
	}

	@Test
	void tsetMemberDAO() {
		System.out.println("이것은 MemberDAO 테스트입니다.");
		
		MemberDAO dao = new MemberDAO();
		
		List<MemberVO> list = dao.getMember();
		for(MemberVO m : list) {
			System.out.println(m);
		}
		
	}

	
	
}
