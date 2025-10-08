package com.example.librarymanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.librarymanagement.infrastructure.entity.Book;
import com.example.librarymanagement.infrastructure.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookRepository repo;
	
	public Book createBook(@RequestBody Book body) {
		return repo.saveAndFlush(body);
	}
	
	public List<Book> getAllBooks(){
		return repo.findAll();
	}
	
	public Book getBookById(Long id) {
		return repo.findById(id).orElseThrow(()->
			new NullPointerException("No ressource found with id: "+id));
	}
	
	public Book updateBookById(Long id, Book body) {
		Book book = getBookById(id);
		body.setId(book.getId());
		body.setTitle(body.getTitle() != null ?
			body.getTitle() : book.getTitle());
		body.setAuthor(body.getAuthor() != null ?
			body.getAuthor() : book.getAuthor());
		body.setPublisher(body.getPublisher() != null ?
			body.getPublisher() : book.getPublisher());
		body.setAgeRestriction(body.getAgeRestriction() != null ?
			body.getAgeRestriction() : book.getAgeRestriction());
		return repo.saveAndFlush(body);
	}
	
	public boolean deleteBook(Long id) {
		if(!repo.existsById(id)) throw
			new NullPointerException("No ressource found with id: "+id);
		repo.deleteById(id);
		return !repo.existsById(id);
	}
}
