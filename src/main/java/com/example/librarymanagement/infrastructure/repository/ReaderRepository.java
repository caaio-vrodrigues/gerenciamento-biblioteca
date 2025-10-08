package com.example.librarymanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagement.infrastructure.entity.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {}
