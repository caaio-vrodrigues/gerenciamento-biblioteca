package com.example.librarymanagement.service;

import java.util.List;

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
	
	public List<Reader> getAllReaders(){
		return repo.findAll();
	}
	
	public Reader getReaderById(Long id) {
		return repo.findById(id).orElseThrow(()->
			new NullPointerException("No ressource found with id: "+id));
	}
	
	public Reader updateReader(Long id, Reader body) {
		Reader reader = getReaderById(id);
		body.setId(id);
		body.setFullName(body.getFullName() != null ? 
			body.getFullName() : reader.getFullName());
		body.setAge(body.getAge() != null ?
			body.getAge() : reader.getAge());
		return repo.saveAndFlush(body);
	}
	
	public boolean deleteReader(Long id) {
		if(!repo.existsById(id)) throw
			new NullPointerException("No ressource found with id: "+id);
		repo.deleteById(id);
		return !repo.existsById(id);
	}
}
