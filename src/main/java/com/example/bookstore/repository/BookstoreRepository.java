package com.example.bookstore.repository;

import com.example.bookstore.model.Bookstore;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookstoreRepository extends JpaRepository<Bookstore, Long>{}
