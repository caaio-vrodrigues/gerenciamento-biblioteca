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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagement.infrastructure.entity.Book;
import com.example.librarymanagement.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

	private final BookService service;
	
	@PostMapping
	public ResponseEntity<Book> newBook(@RequestBody Book body){
		return ResponseEntity.ok(service.createBook(body));
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> listBooks(){
		return ResponseEntity.ok(service.getAllBooks());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> searchBookById(@PathVariable Long id){
		return ResponseEntity.ok(service.getBookById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody Book body){
		return ResponseEntity.ok(service.updateBookById(id, body));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excludeBookById(@PathVariable Long id){
		return ResponseEntity.ok(service.deleteBook(id));
	}
}
