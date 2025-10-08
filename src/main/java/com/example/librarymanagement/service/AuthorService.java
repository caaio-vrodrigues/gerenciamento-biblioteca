package com.example.librarymanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.librarymanagement.infrastructure.entity.Author;
import com.example.librarymanagement.infrastructure.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {

	private final AuthorRepository repo;
	
	public Author createAuthor(@RequestBody Author body) {
		return repo.saveAndFlush(body);
	}
	
	public List<Author> getAllAuthors(){
		return repo.findAll();
	}
	
	public Author getAuthorById(Long id) {
		return repo.findById(id).orElseThrow(()->
			new NullPointerException("No ressource found with id: "+id));
	}
	
	public Author updateAuthorById(Long id, Author body) {
		Author author = getAuthorById(id);
		body.setId(author.getId());
		body.setBirthday(body.getBirthday() != null ?
			body.getBirthday() : author.getBirthday());
		body.setCountry(body.getCountry() != null ?
			body.getCountry() : author.getCountry());
		body.setFullName(body.getFullName() != null ?
			body.getFullName() : author.getFullName());
		return repo.saveAndFlush(body);
	}
	
	public boolean deleteAuthor(Long id) {
		if(!repo.existsById(id)) throw
			new NullPointerException("No ressource found with id: "+id);
		repo.deleteById(id);
		return !repo.existsById(id);
	}
}
