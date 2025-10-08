package com.example.librarymanagement.service;

import org.springframework.stereotype.Service;

import com.example.librarymanagement.infrastructure.entity.Reader;
import com.example.librarymanagement.infrastructure.repository.ReaderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReaderService {

	private final ReaderRepository repo;
	
	public Reader createReader(Reader body) {
		return repo.saveAndFlush(body);
	}
}
