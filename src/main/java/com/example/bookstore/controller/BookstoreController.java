package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookstoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* The annotation tells Spring : "look this class has a special role : "
*     - the special role :
*           o @RestController it's a combination of :
*               + @Controller   - tells Spring that this class is a web controller
*                                 (= manages HTTP requests)
*               + @ResponseBody -  tells that the methods returns JSON / text as
*                                  an HTTP response
*              In other words  :
*               - without @RestController, Spring would try to find an HTML/template file returned
*               - with @RestController, anything you return (ex Book, List<Book>) it's automatically
*                 converted in JSON and sent to the client
*
*           o @RequestMapping("/books") :
*               + tells that every endpoint from this controller starts from /books.
*                  (for example you are going to see in the code @GetMapping which means in this case :
*                   GET /books. If you write @GetMapping("/{id}") this will mean GET /books/id
*               + without it the routes would've start from / (from the root)
*               + @PathVariable - tells spring to take a part from URL and map it to a parameter from
*                                 the method
*               + @RequestBody  - tells spring to take the JSON sent from the client in the request body
*                                 and transform it into a Java object (like it takes the JSON and spring
*                                 creates an object Book, and it populates it with the received values)
*
*           o ResponseEntity : wrapper of the HTTP response. It allows us to control the status code +
*                              body + headers
*                             ex : - ResponseEntity.ok(book)  ---> status 200 + JSON with the book
*                                  - ResponseEntity.status(HttpStatus.CREATED).body(book) --> status 201 + JSON
*                                  - ResponseEntity.noContent() ---> status 204 without body --> nothing to return
*  */

@RestController
@RequestMapping("/books")
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){ return ResponseEntity.ok(bookstoreService.getAllBooks());}

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){ return ResponseEntity.ok(bookstoreService.getBookById(id)); }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookstoreService.createBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookBody){
        return ResponseEntity.ok(bookstoreService.updateBook(id, bookBody));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookstoreService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
