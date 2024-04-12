package com.exercise.libraryDBSpringBoot.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.libraryDBSpringBoot.model.Books;
import com.exercise.libraryDBSpringBoot.model.Transaction;
import com.exercise.libraryDBSpringBoot.model.Users;
import com.exercise.libraryDBSpringBoot.repo.BookRepo;
import com.exercise.libraryDBSpringBoot.repo.TransactionRepo;
import com.exercise.libraryDBSpringBoot.repo.UserRepo;

@Service
public class LibraryService {
	@Autowired
	public UserRepo userRepo;
	@Autowired
	public BookRepo bookRepo;
	@Autowired
	public TransactionRepo transRepo;
	
	public String uploadData() {
		List<Users> users = new ArrayList<>( List.of(new Users(101,"1234567890", "Jeeva", "premium",0),
				new Users(102,"1234444451", "Karthik", "normal",0),
						new Users(103,"1345678901",  "Pugazh", "premium",0),
						new Users(104,"1456789012",  "Yukhan", "normal",0))
						);
		List<Books> books = new ArrayList<>( List.of(new Books(10001, "Hazil", 2),
							new Books( 10002, "Rainy", 2),
							new Books(10003, "Kalangal", 2),
							new Books(10004,"Thirukural",2)));
		
		userRepo.saveAll(users);
		bookRepo.saveAll(books);
		return "uploaded successfully";
	}
	
	public List<Books> getBookList()
	{
		return bookRepo.findAll();
	}
	
	public List<Users> getUserList()
	{
		return userRepo.findAll();
	}
	
	public List<Transaction> getTransactionList()
	{
		return transRepo.findAll();
	}
	
	
	public String addBook(Books book,String s)
	{
		if(s.equals("Jeeva"))
		{
			bookRepo.save(book);
			return "Book Added Successfully";
		}
		else
		{
			return "Not authorised to add a book "+ s;
		}
	}
	
	public String signUp(Users user) {
		Users oldUser= userRepo.findByMobileNo(user.getMobileNo()).orElse(new Users());
		if(oldUser.getMobileNo()== null) {
			 userRepo.save(user);
			 return "Signed up successfully!!!";
		}
		else 
		{
			return "User Already exist";
		}
		
	}
	
	@Transactional
	public String deleteSubscription(Users users) {
		Users user= userRepo.findByMobileNo(users.getMobileNo()).orElse(new Users());
		if(user.getMobileNo()!=null)
		{ 
			if(user.getBookId()== 0) {
		
			userRepo.deleteByMobileNo(user.getMobileNo() );
			return "Subscription cancelled successfully";
		    }
			else {
				return "User holding a book please do return and unsubscribe";
			}
		}
        else if (user.getMobileNo()==null){
	     return "Oops!!! no user found";	
	    }
		return "done";
	  }
	
	@Transactional
	public String borrowBook(int bookId,String mobileNo) {
		
		Books book = bookRepo.findById(bookId).orElse(new Books());
		Users user= userRepo.findByMobileNo(mobileNo).orElse(new Users());
		int newQuantity=book.getQuantity()-1;
		
		LocalTime time= LocalTime.now();
		
		if(book.getQuantity()!=0)
		{
			if(user.getBookId()==0) {
				
		
				if(book.getQuantity()==1)
				{
					//Users user= userRepo.findByMobileNo(mobileNo).orElse(new Users());
					if(user.getSubscription()=="premium") {
						userRepo.updateBookId(mobileNo, bookId);
						bookRepo.updateBookQuantity(bookId,newQuantity);
						
						Transaction trans = new Transaction(time,bookId,mobileNo,"Borrow");
						transRepo.save(trans);
						
					}
					else {
						return "Book quantity is less please upgrade your plan to get it";
					}
				}
				else if(book.getQuantity()>1) {
					
					userRepo.updateBookId(mobileNo, bookId);
					bookRepo.updateBookQuantity(bookId,newQuantity);
					
					Transaction trans = new Transaction(time,bookId,mobileNo,"Borrow");
					transRepo.save(trans);
				}
			    } 
			else 
			{
					return "User already holding a book";
				}
		}
		else if(book.getQuantity()==0) {
		          return "Book is not available";
		}
		
		return " Operation Successfull";
	
		}
	
	
	@Transactional
	public String returnBook(int bookId,String mobileNo) {
		Users user= userRepo.findByMobileNo(mobileNo).orElse(new Users());
		int userBookId=user.getBookId();
		LocalTime time= LocalTime.now();
		
		Books book=bookRepo.findById(bookId).orElse(new Books());
		int newQuantity=book.getQuantity()+1;
		
		if(userBookId==bookId) {
			userRepo.updateBookId(mobileNo, 0);
			bookRepo.updateBookQuantity(bookId, newQuantity);
			
			Transaction trans = new Transaction(time,bookId,mobileNo,"Return");
			transRepo.save(trans);
			
			return "Returned successfully";
		}
		else {
			return "User does not hold a book to return";
		}
		}
	
	@Transactional
	public String upgradePlan(Users users) {
		Users user= userRepo.findByMobileNo(users.getMobileNo()).orElse(new Users());
		String sub=user.getSubscription();
		if(sub=="normal") {
			userRepo.updateSub(user.getMobileNo());
			return "Upgraded to premium successfully";
		}
		else {
			return " Already a premium user.";
		}
	}
	
	
		
	
}

 