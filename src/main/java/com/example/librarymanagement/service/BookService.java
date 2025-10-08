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
	private final SessionService sessionService;
	private final AuthorService authorService;
	private final PublisherService publisherService;
	
	public Book createBook(@RequestBody Book body) {
		body.setSession(sessionService.getSessionById(body
			.getSession().getId()));
		body.setAuthor(authorService.getAuthorById(body
			.getAuthor().getId()));
		body.setPublisher(publisherService.getPublisherById(body
			.getPublisher().getId()));
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
		body.setAuthor(authorService.getAuthorById(body.getAuthor() != null ? 
			body.getAuthor().getId() : book.getAuthor().getId()));
		body.setPublisher(publisherService.getPublisherById(body.getPublisher() != null ?
			body.getPublisher().getId() : book.getAuthor().getId()));
		body.setAgeRestriction(body.getAgeRestriction() != null ?
			body.getAgeRestriction() : book.getAgeRestriction());
		body.setSession(sessionService.getSessionById(body.getSession() != null ?
			body.getSession().getId() : book.getSession().getId()));
		return repo.saveAndFlush(body);
	}
	
	public boolean deleteBook(Long id) {
		if(!repo.existsById(id)) throw
			new NullPointerException("No ressource found with id: "+id);
		repo.deleteById(id);
		return !repo.existsById(id);
	}
}
