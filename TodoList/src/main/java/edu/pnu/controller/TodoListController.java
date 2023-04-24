package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.TodoList;
import edu.pnu.service.TodoListService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
//@RequestMapping("/api/todos")
public class TodoListController {

	@Autowired
	TodoListService ts;
	
	@GetMapping("/api/todos")
	public List<TodoList> getLists() {
		return ts.getLists();
	}
	
	@PostMapping("/api/todos")
	public TodoList addList(@RequestBody TodoList todolist) { //post 방식일 때는 @RequestBody 있어야 클라이언트의 요청을 바인딩할 수 있다.
																//message converter가 requestbody를 todolist 타입의 객체로 변환시켜줌
																//즉, JSON을 객체형태로 변환
		System.out.println("Received todo item: " + todolist);
		return ts.addList(todolist);
	}
	
	@PutMapping("/api/todos")
	public TodoList updateList(@RequestBody TodoList todolist) {
		System.out.println("Received item to update: " + todolist);
		return ts.updateList(todolist);
	}
	
	@DeleteMapping("/api/todos/{id}")
	public void deleteList(@RequestBody @PathVariable TodoList todolist) {
		System.out.println("Received item to delete: " + todolist);
		ts.deleteList(todolist);
	}
	
}
