package com.example.librarymanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagement.infrastructure.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {}
