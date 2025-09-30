package com.example.bookstore.service;
import java.util.List;

import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookstoreRepository;
import org.springframework.stereotype.Service;


// Service is being used so it can use the data so it can implement the application logic
// From my understanding we are using the repository as a support for methods, like we would've used
// addAll(), or sort(), or another helpful method for some datatype.
// But in this case we are using that so we can manipulate data easily without having to implement
// the actual queries.
@Service
public class BookstoreService {

        private final BookstoreRepository bookstoreRepository;

        public BookstoreService(BookstoreRepository bookstoreRepository){
            this.bookstoreRepository = bookstoreRepository;
        }

        public List<Book> getAllBooks() { return bookstoreRepository.findAll(); }

        public Book getBookById(Long id){
            return bookstoreRepository.findById(id)
                    .orElseThrow(() -> new BookNotFoundException("Book was not found with the provided id : " + id)); //custom error exception
        }

        public Book createBook(Book book){ return bookstoreRepository.save(book); }

        /*
        *
        * Basically here, what do I do is that I give those parameters : @id, @updatedBookBody to the method,
        * but those are not the exact object in the database. What this does is that : 
        * @id - represents the id of the book that I would like to modify
        * @updatedBookBody - it's the actual new object containing the new details of the already stored book, 
        *               that I am willing
        *              - to be even more specific, I query to the Database to update the Book at that @id
        *               with the new body of the book which is @updatedBookBody
        * Therefore the steps implemented here are : 
        * > Step 1 : Find the book that is already in the DB using getBookById method that I've implemented
        *           here. Maybe the id is not actually the right one, or stored, so we throw an exception
        *           in that situation
        * > Step 2 : If we did find the book, we set its title,category,etc. with the ones that we are willing
        *           to change (The object Book that it has a Body containing the data that we've changed or not)
        *           So we are getting, those values, and we are setting them to the new resulted object.
        * > Step 3 : Consider bookstoreRepository as our own secretary. When we create this bookToBeUpdated object
        *           we create it in the memory, which is not persistent. To save it in the database, we tell our
        *           secretary to put that data in place.
        *           Considering that analogy with the Dog and bark() method, and blueprints and objects:
        *           Here for the bookstoreRepository we can consider it as a blueprint for a responsibility.
        *           ("doesn't exist as a physical object")
        *            Only in this case because we are still making a paralel between Real thing object and Responsibility
        *           object, we will continue considering it as the secretary which has those special methods, to certify
        *           the salary that we are paying her within our BookStore :D
        * 
        * */
        public Book updateBook(Long id, Book updatedBookBody){
            Book bookToBeUpdated = getBookById(id); //S1
            bookToBeUpdated.setTitle(updatedBookBody.getTitle()); //S2
            bookToBeUpdated.setCategory(updatedBookBody.getCategory());
            bookToBeUpdated.setNoOfPages(updatedBookBody.getNoOfPages());

            return bookstoreRepository.save(bookToBeUpdated); //S3
        }

        public void deleteBook(Long id) { bookstoreRepository.deleteById(id); }
}
