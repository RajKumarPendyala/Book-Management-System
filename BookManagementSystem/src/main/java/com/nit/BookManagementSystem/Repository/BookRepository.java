package com.nit.BookManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nit.BookManagementSystem.Entity.BookEntity;


public interface BookRepository extends JpaRepository<BookEntity, Integer>{
	public List<BookEntity> findByActiveSW(String status); 
	boolean existsByBookName(String bookName);
	@Modifying
	@Query("UPDATE BookEntity b SET b.bookName = :bookName, b.bookAuthor = :bookAuthor, b.bookPrice = :bookPrice WHERE b.bookId = :bookId")
	int update(@Param("bookId") Integer bookId, @Param("bookName") String bookName, @Param("bookAuthor") String bookAuthor, @Param("bookPrice") Double bookPrice);
}
