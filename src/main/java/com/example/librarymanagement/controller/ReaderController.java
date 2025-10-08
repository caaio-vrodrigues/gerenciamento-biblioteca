package com.example.librarymanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
