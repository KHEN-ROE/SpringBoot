package edu.pnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Person;
import lombok.extern.slf4j.Slf4j;

//@Slf4j // 간단한 로그를 자바 랭귀지로 제공. 근데 잘 안씀
@RestController // 실행함과 동시에 패키지 내 모든 클래스에 @RestController어노테이션 붙어있는지 검사하고 자동으로 new해서 인스턴스 만들어서 컨테이너에 올린다.(@Service도 마찬가지)
//@Autowired -  컨테이너에 있는 객체 찾아서 외부에서 넣어준다. 객체 생성 내가 직접 안하고. 외부에서 넣어준다고 해서 dependency injection
//Rest API 방식임. 그 외에도 @Controller, @Service... 있음
//부트에서는 주고받는 데이터를 제이슨 방식 씀. 객체, 배열 등을 리턴하면 제이슨으로 변환해줌 스프링이 
public class BoardController {
	
	public BoardController() {
		System.out.println("==> BoardController 생성");
//		log.info("==> BoardController 생성")
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello! : " +name ;
	}
	
	@PostMapping("/hello") // Post 방식으로 호출하려면? 1. form 만든다. 혹은 2. Postman 설치
	public String hello1(String name) {
		return "Hello Post : " +name ;
	}
	
	@GetMapping("/getPerson")
	public Person getPerson() {
		Person p = new Person("홍길동", 2000, "백수", "선플3");
		return p;
	}
	
	@GetMapping("/getPersons")
	public List<Person> getPersons() {
		List<Person> list = new ArrayList<>();
		list.add(new Person("홍길동", 2000, "백수", "선플"));
		list.add(new Person("홍길동2", 23000, "백수2", "선플2"));
		list.add(new Person("홍길동2", 23000, "백수2", "선플2"));
		
		Person p = new Person("홍길동3", 24000, "백수3", "선플3");
		list.add(p);
		Person p1 = new Person("홍길동4", 25000, "백수4", "선플4");
		list.add(p1);
		
		list.add(new Person("홍길동5", 26000, "백수5", "선플5"));
		
		return list;
	}
}
