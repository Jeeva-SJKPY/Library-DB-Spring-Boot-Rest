package com.exercise.libraryDBSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.libraryDBSpringBoot.model.Books;
import com.exercise.libraryDBSpringBoot.model.Transaction;
import com.exercise.libraryDBSpringBoot.model.Users;
import com.exercise.libraryDBSpringBoot.service.LibraryService;

@RestController
@Controller
public class LibraryContoller {
	@Autowired
	private LibraryService service;
	
	public static class BookRequest {
	    private int bookId;
	    private String mobileNo;
	    
	    public int getBookId() {
			return bookId;
		}
		public void setBookId(int bookId) {
			this.bookId = bookId;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		

	    }
	
	@GetMapping("uploadData")
	public String uploadData() {
		return service.uploadData();
		
	}
	
	@GetMapping("bookCatelog")
	public List<Books> getBookList()
	{
		return service.getBookList();
	
	}
	@GetMapping("userTable")
	public List<Users> getUserList()
	{
		return service.getUserList();
	
	}
	@GetMapping("transTable")
	public List<Transaction> getTransList()
	{
		return service.getTransactionList();
	
	}
	
	@PostMapping("Admin/AddBook/{admin}")
	public String addBook(@RequestBody Books book,@PathVariable("admin")String admin) {
		
		return service.addBook(book, admin);
		
	}
	
	@PostMapping("signUp")
	public String signUp(@RequestBody Users user) {
		
		return service.signUp(user);
		
	}
	
	@PutMapping("borrowBook")
	public String borrowBook(@RequestBody BookRequest request) {
	    return service.borrowBook(request.getBookId(), request.getMobileNo());
	}
	

	@PutMapping("returnBook")
	public String returnBook(@RequestBody BookRequest request ) {
		return service.returnBook(request.getBookId(), request.getMobileNo());
	}
	
	@PutMapping("upgradePlan")
	public String upgradePlan(@RequestBody Users user) {
		return service.upgradePlan(user);
	}
	
	@DeleteMapping("deleteSubscription")
	public String deleteSubscription(@RequestBody Users user) {
		return service.deleteSubscription(user);
	}
}
