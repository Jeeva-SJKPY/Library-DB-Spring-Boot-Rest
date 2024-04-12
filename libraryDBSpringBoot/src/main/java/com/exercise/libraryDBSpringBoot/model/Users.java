
 package com.exercise.libraryDBSpringBoot.model;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
@Scope("prototype")
@Entity
public class Users {
	
	@Id
	private int userId;
	@Column(unique=true)
	private String mobileNo;
	private String userName;
	private String subscription;
	private int bookId;
	
//	@OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "book_id")
//	private Books book;
	

}
