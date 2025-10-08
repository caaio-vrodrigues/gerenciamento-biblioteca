package com.example.librarymanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagement.infrastructure.entity.BookRental;
import com.example.librarymanagement.service.BookRentalService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class BookRentalController {

	private final BookRentalService service;
	
	@PostMapping
	public ResponseEntity<BookRental> createBookRental(
		@RequestBody BookRental body
	){
		return ResponseEntity.ok(service.rentingBook(body));
	}
	
	@GetMapping
	public ResponseEntity<List<BookRental>> listBookRentals(){
		return ResponseEntity.ok(service.getAllBookRents());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookRental> searchBookById(@PathVariable Long id){
		return ResponseEntity.ok(service.getBookRentById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BookRental> bookDevolution(@PathVariable Long id){
		return ResponseEntity.ok(service.returningBook(id));
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<BookRental> editBookRental(
		@PathVariable Long id, 
		@RequestBody BookRental body
	){
		return ResponseEntity.ok(service.updateBookRental(id, body));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excludeBookRental(@PathVariable Long id){
		return ResponseEntity.ok(service.deleteBookRental(id));
	}
}
