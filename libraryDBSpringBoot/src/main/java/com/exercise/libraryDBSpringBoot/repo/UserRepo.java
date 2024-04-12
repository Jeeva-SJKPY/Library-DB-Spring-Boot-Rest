package com.exercise.libraryDBSpringBoot.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exercise.libraryDBSpringBoot.model.Users;


@Repository
public interface UserRepo  extends JpaRepository<Users,Integer>{
	@Modifying
	@Query(value="UPDATE Users SET book_id = ?2 WHERE mobile_no =?1", nativeQuery=true)
	void updateBookId(String mobileNo,int BookId);
	
	@Modifying
	@Query(value="UPDATE Users SET subscription ='premium' WHERE mobile_no=?1", nativeQuery = true)
	void updateSub(String mobileNo);
	
	@Modifying 
	@Query(value="delete from Users where mobile_no=?1", nativeQuery=true)
	void deleteByMobileNo(String mobileNo);
	
    @Query(value= "select * from Users where mobile_no=?1", nativeQuery = true)
	Optional<Users> findByMobileNo(String mobileNo);

}
