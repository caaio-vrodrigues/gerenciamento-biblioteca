package com.example.librarymanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarymanagement.infrastructure.entity.Publisher;
import com.example.librarymanagement.infrastructure.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherService {

	private final PublisherRepository repo;
	
	public Publisher createPublisher(Publisher body) {
		return repo.saveAndFlush(body);
	}
	
	public List<Publisher> getAllPublishers() {
		return repo.findAll();
	}
	
	public Publisher getPublisherById(Long id) {
		return repo.findById(id).orElseThrow(()->
			new NullPointerException("No ressource found with id: "+id));
	}
	
	public Publisher updatePublisher(Long id, String name) {
		Publisher publisher = getPublisherById(id);
		publisher.setName(name);
		return repo.saveAndFlush(publisher);
	}
	
	public boolean deletePublisher(Long id) {
		if(!repo.existsById(id)) throw
			new NullPointerException("No ressource found with id: "+id);
		repo.deleteById(id);
		return !repo.existsById(id);
	}
}
