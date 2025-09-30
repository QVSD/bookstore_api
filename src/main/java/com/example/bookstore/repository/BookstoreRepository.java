package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


//Repository is here because it knows how to speak to the DB
//the operations implemented there are strictly about accessing data:
//  findAll, findById, save, delete

public interface BookstoreRepository extends JpaRepository<Book, Long>{}
