package com.nit.BookManagementSystem.Service;

import java.util.List;

import com.nit.BookManagementSystem.Entity.BookEntity;

public interface BookService {
	public List<BookEntity> getAllBooks();
	public boolean updateBook(BookEntity book, Integer BookId) throws Exception;
	public void deleteBook(Integer bookId);
	public boolean saveBook(BookEntity book) throws Exception;
	public BookEntity getBookById(Integer bookId);
}
