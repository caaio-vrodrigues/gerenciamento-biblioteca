package com.example.librarymanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarymanagement.infrastructure.entity.Session;
import com.example.librarymanagement.infrastructure.repository.SessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {

	private final SessionRepository repo;
	
	public Session createSession(Session body) {
		return repo.saveAndFlush(body);
	}
	
	public List<Session> getAllSessions(){
		return repo.findAll();
	}
	
	public Session getSessionById(Long id) {
		return repo.findById(id).orElseThrow(()->
			new NullPointerException("No ressource found with id: "+id));
	}
	
	public Session updateSession(Long id, Session body) {
		Session session = getSessionById(id);
		body.setId(session.getId());
		body.setGender(body.getGender() != null ? 
			body.getGender() : session.getGender());
		return repo.saveAndFlush(body);
	}
	
	public boolean deleteSession(Long id) {
		if(!repo.existsById(id)) throw
			new NullPointerException("No ressource found with id: "+id);
		repo.deleteById(id);
		return !repo.existsById(id);
	}
}
