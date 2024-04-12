package com.exercise.libraryDBSpringBoot.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Component
@Scope("prototype")
@Entity
public class Transaction {
	public Transaction(LocalTime time, int bookId, String mobileNo, String string) {
		timeStamp =time;
		this.bookId=bookId;
		this.mobileNo=mobileNo;
		action=string;
		}
	@Id
	@Column(columnDefinition = "TIME")
	private LocalTime timeStamp;
	private int bookId;
	private String mobileNo;
	private String action;

}
