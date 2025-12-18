package com.CompanyName.SwaggerPractice.Controller;

import com.CompanyName.SwaggerPractice.Entity.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "API for managing books")
public class BookController {

    // Using a simple in-memory map as a database
    private final Map<Integer, Book> bookStore = new HashMap<>();

    // Initialize with some data
    public BookController() {
        bookStore.put(1, new Book(1, "The Lord of the Rings", "J.R.R. Tolkien"));
        bookStore.put(2, new Book(2, "Dune", "Frank Herbert"));
    }

    @Operation(summary = "Get all books", description = "Returns a list of all books in the store.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookStore.values().stream().toList());
    }

    @Operation(summary = "Get a book by its ID", description = "Fetches a single book based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the book",
                    content = {
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Book.class)
                        )
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Book book = bookStore.get(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new book", description = "Creates a new book in the store.")
    @ApiResponse(responseCode = "201", description = "Book created successfully")
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        // In a real app, you'd generate a new ID
        bookStore.put(newBook.getId(), newBook);
        return ResponseEntity.status(201).body(newBook);
    }
}