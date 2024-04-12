package com.exercise.libraryDBSpringBoot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exercise.libraryDBSpringBoot.model.Books;

@Repository
public interface BookRepo extends JpaRepository<Books,Integer>{
	
	@Modifying
	@Query("update Books set quantity=?2 where bookId=?1")
	void updateBookQuantity(int bookId,int newQuantity);

}
