package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.DBoard;
import edu.pnu.persistence.BoardRepository;

@Service
public class DBoardService {

	@Autowired
	BoardRepository br;
	
	public List<DBoard> getBoards(){
		return (List<DBoard>) br.findAll();
	}
	
	public DBoard getBoard(Long seq) {
		return br.findById(seq).get();
	}
	
	
}
