package com.example.librarymanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarymanagement.infrastructure.entity.Book;
import com.example.librarymanagement.infrastructure.entity.BookRental;
import com.example.librarymanagement.infrastructure.entity.Reader;
import com.example.librarymanagement.infrastructure.repository.BookRentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookRentalService {
	
	private final BookRentalRepository repo;
	private final BookService bookService;
	private final ReaderService readerService;
	
	public BookRental rentingBook(BookRental body) {
		body.setBookRent(LocalDateTime.now());
		body.setBook(bookService.getBookById(body.getBook().getId()));
		body.setReader(readerService.getReaderById(body.getReader().getId()));
		return repo.saveAndFlush(body);
	}
	
	public List<BookRental> getAllBookRents(){
		return repo.findAll();
	}
	
	public BookRental getBookRentById(Long id){
		return repo.findById(id).orElseThrow(()->
			new NullPointerException("No ressource found with id: "+id));
	}
	
	public BookRental updateBookRental(Long id, BookRental body) {
		BookRental rent = getBookRentById(id);
		Book book = bookService.getBookById(body.getBook() != null ? 
			body.getBook().getId() : rent.getBook().getId());
		Reader reader = readerService.getReaderById(body.getBook() != null ? 
			body.getBook().getId() : rent.getBook().getId());
		body.setId(rent.getId());
		body.setBook(book);
		body.setReader(reader);
		body.setBookRent(body.getBookRent() != null ? 
			body.getBookRent() : rent.getBookRent());
		body.setBookDevolution(body.getBookDevolution() != null ? 
			body.getBookDevolution() : rent.getBookDevolution());
		return repo.saveAndFlush(body);
	}
	
	public BookRental returningBook(Long id) {
		BookRental rent = getBookRentById(id);
		rent.setBookDevolution(LocalDateTime.now());
		return repo.saveAndFlush(rent);
	}
	
	public boolean deleteBookRental(Long id) {
		if(!repo.existsById(id)) throw
			new NullPointerException("No ressource found with id: "+id);
		repo.deleteById(id);
		return !repo.existsById(id);
	}
}
