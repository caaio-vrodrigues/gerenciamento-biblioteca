package com.example.librarymanagement.infrastructure.entity;

import java.time.LocalDate;

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
@Table(name="author")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="full_name", nullable=false)
	private String fullName;
	
	@Column(name="birthday", nullable=false)
	private LocalDate birthday;
	
	@Column(name="country", nullable=false)
	private String country;
}
