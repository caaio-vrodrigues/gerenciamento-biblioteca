package com.example.librarymanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagement.infrastructure.entity.Author;
import com.example.librarymanagement.service.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
	
	private final AuthorService service;
	
	@PostMapping
	public ResponseEntity<Author> newAuthor(@RequestBody Author body){
		return ResponseEntity.ok(service.createAuthor(body));
	}
	
	@GetMapping
	public ResponseEntity<List<Author>> listAuthor(){
		return ResponseEntity.ok(service.getAllAuthors());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> searchAuthorById(@PathVariable Long id){
		return ResponseEntity.ok(service.getAuthorById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody Author body){
		return ResponseEntity.ok(service.updateAuthorById(id, body));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excludeAuthorById(@PathVariable Long id){
		return ResponseEntity.ok(service.deleteAuthor(id));
	}
}
