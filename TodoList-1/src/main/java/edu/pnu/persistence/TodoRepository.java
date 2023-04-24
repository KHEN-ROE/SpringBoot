package edu.pnu.persistence;

import org.springframework.data.repository.CrudRepository;

import pnu.edu.domain.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}