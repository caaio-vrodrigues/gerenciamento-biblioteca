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

import com.example.librarymanagement.infrastructure.entity.Publisher;
import com.example.librarymanagement.service.PublisherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherController {
	
	private final PublisherService service;
	
	@PostMapping
	public ResponseEntity<Publisher> newPublisher(@RequestBody Publisher body){
		return ResponseEntity.ok(service.createPublisher(body));
	}
	
	@GetMapping
	public ResponseEntity<List<Publisher>> listPublishers(){
		return ResponseEntity.ok(service.getAllPublishers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Publisher> searchPublisher(@PathVariable Long id){
		return ResponseEntity.ok(service.getPublisherById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Publisher> editPublisher(
		@PathVariable Long id, 
		@RequestParam String name
	){
		return ResponseEntity.ok(service.updatePublisher(id, name));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excludePublisher(@PathVariable Long id){
		return ResponseEntity.ok(service.deletePublisher(id));
	}
}
