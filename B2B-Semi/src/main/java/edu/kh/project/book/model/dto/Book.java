package edu.kh.project.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Book {

	private int bookId;
	private String title;
	private String isbn;
	private String author;
	private String publisher;
	private String pubDate;
	private String description;
	private String coverUrl;
	private String firstCategory;
	private String secondCategory;
	private String isDeleted;
	private String createdAt;
	private String updatedAt;
	private int customerReviewRank;
	private int rowNum;
	
}
