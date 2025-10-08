package com.example.librarymanagement.infrastructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name="author", nullable=false)
	private Author author;
	
	@ManyToOne
	@JoinColumn(name="publisher", nullable=false)
	private Publisher publisher;
	
	@Column(name="age_restriction", nullable=false)
	private Integer ageRestriction;
	
	@ManyToOne
	@JoinColumn(name="session", nullable=false)
	private Session session;
	
	@Column(name="release_date", nullable=false)
	private LocalDate releaseDate;
}
