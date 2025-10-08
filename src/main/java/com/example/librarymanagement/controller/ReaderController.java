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

import com.example.librarymanagement.infrastructure.entity.Reader;
import com.example.librarymanagement.service.ReaderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reader")
public class ReaderController {
	
	private final ReaderService service;

	@PostMapping
	public ResponseEntity<Reader> newReader(
		@RequestBody Reader body
	){
		return ResponseEntity.ok(service.createReader(body));
	}
	
	@GetMapping
	public ResponseEntity<List<Reader>> listAllReaders(){
		return ResponseEntity.ok(service.getAllReaders());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Reader> searchReaderById(@PathVariable Long id){
		return ResponseEntity.ok(service.getReaderById(id));
	}
	
	@PutMapping
	public ResponseEntity<Reader> editReader(
		@RequestParam Long id,
		@RequestBody Reader body
	){
		return ResponseEntity.ok(service.updateReader(id, body));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excludeReader(@PathVariable Long id){
		return ResponseEntity.ok(service.deleteReader(id));
	}
}
