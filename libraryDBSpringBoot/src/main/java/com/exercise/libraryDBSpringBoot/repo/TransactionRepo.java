package com.exercise.libraryDBSpringBoot.repo;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exercise.libraryDBSpringBoot.model.Transaction;
import com.exercise.libraryDBSpringBoot.model.Users;


@Repository
public interface TransactionRepo extends JpaRepository<Transaction,LocalTime> { 
 
}
