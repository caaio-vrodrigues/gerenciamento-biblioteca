package com.example.librarymanagement.infrastructure.entity;

import java.time.LocalDateTime;

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
@Table(name="book_rental")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRental {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="rentor", nullable=false)
	private Reader reader;
	
	@ManyToOne
	@JoinColumn(name="book", nullable=false)
	private Book book;
	
	@Column(name="book_rent", nullable=false)
	private LocalDateTime bookRent;
	
	@Column(name="book_devolution")
	private LocalDateTime bookDevolution;
}
