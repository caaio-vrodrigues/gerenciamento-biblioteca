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

import com.example.librarymanagement.infrastructure.entity.Session;
import com.example.librarymanagement.service.SessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {
	
	private final SessionService service;
	
	@PostMapping
	public ResponseEntity<Session> newSession(@RequestBody Session body){
		return ResponseEntity.ok(service.createSession(body));
	}
	
	@GetMapping
	public ResponseEntity<List<Session>> listSessions(){
		return ResponseEntity.ok(service.getAllSessions());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Session> searchSessionById(@PathVariable Long id){
		return ResponseEntity.ok(service.getSessionById(id));
	}
	
	@PutMapping
	public ResponseEntity<Session> editSession(
		@RequestParam Long id,
		@RequestBody Session body
	){
		return ResponseEntity.ok(service.updateSession(id, body));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excludeSession(@PathVariable Long id){
		return ResponseEntity.ok(service.deleteSession(id));
	}
}
