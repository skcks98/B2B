package edu.kh.project.book.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.book.model.dto.Book;

@Mapper
public interface BookMapper {

	void insertBook(Book book);


	
	
}
