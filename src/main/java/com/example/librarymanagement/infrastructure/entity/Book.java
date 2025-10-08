package com.example.librarymanagement.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="author", nullable=false)
	private String author;
	
	@Column(name="publisher", nullable=false)
	public String publisher;
	
	@Column(name="age_restriction", nullable=false)
	public Integer ageRestriction;
}
