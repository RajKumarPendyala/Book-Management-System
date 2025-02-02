package com.nit.BookManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.BookManagementSystem.Entity.BookEntity;
import com.nit.BookManagementSystem.Repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepo;
	//soft delete
	@Override 
	public List<BookEntity> getAllBooks(){
		return bookRepo.findByActiveSW("Y");
	}
	//@Override
	//public List<BookEntity> getAllBooks() {
	//	return bookRepo.findAll();
	//}
	@Override
	@Transactional
	public boolean updateBook(BookEntity book, Integer bookId) throws Exception{
//		BookEntity getId = bookRepo.save(book);
//        if(getId != null) {
//        	return false;
//        }
//        return true;
	    int response = bookRepo.update(bookId, book.getBookName(), book.getBookAuthor(), book.getBookPrice());
	    if(response == 0) {
	    	return false;
	    }
	    return true;
	}
	@Override
	public boolean saveBook(BookEntity book) throws Exception{
        if (bookRepo.existsByBookName(book.getBookName())) {
            throw new Exception("Book with this name already exists!");
        }
        book.setActiveSW("Y");
        BookEntity getId = bookRepo.save(book);
        if(getId != null) {
        	return false;
        }
        return true;
	}

	@Override
	public void deleteBook(Integer bookId){
		bookRepo.deleteById(bookId); //hardDelete
//		Optional<BookEntity> findById = bookRepo.findById(bookId);
//		if(findById.isPresent()){
//			BookEntity book = findById.get();
//			book.setActiveSW("N");
//			bookRepo.save(book);
//		}
	}
	public BookEntity getBookById(Integer bookId){
		Optional<BookEntity> findById = bookRepo.findById(bookId);
		if(findById.isPresent()){
			return findById.get();
		}
		return null;
	}
}
