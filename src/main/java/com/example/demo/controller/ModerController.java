package com.example.demo.controller;

import com.example.demo.payload.BookDTO;
import com.example.demo.util.Path;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.demo.controller.ModerControllerImpl.*;

@RequestMapping(Path.BASE_PATH_MODER)
@PreAuthorize("hasAuthority('BOOK_CRUD')")
public interface ModerController {
    @PostMapping(BOOK)
    ResponseEntity<?> addBook(@RequestBody @Valid BookDTO bookDTO);

    @PostMapping(BOOK_ID)
    ResponseEntity<?> addBookCount(@RequestParam Integer count,
                                   @PathVariable UUID Id);

    @PutMapping(BOOK_ID)
    ResponseEntity<?> updateBook(@RequestParam String name,
                                 @RequestParam String author,
                                 @PathVariable UUID Id);

    @GetMapping(BOOK)
    ResponseEntity<?> getBooks();

    @GetMapping(BOOK_ID)
    ResponseEntity<?> getBooks(@PathVariable UUID Id);

    @GetMapping(SEARCH_NAME)
    ResponseEntity<?> getBookByName(@RequestParam String name);

    @GetMapping(SEARCH_AUTHOR)
    ResponseEntity<?> getBookByAuthor(@RequestParam String author);

    @PutMapping(CONFIRM_BOOKING)
    ResponseEntity<?> confirmBooking(@PathVariable UUID Id);

    @GetMapping(BOOKING)
    ResponseEntity<?> getBooking();

    @GetMapping(BOOKING_DEADLINE)
    ResponseEntity<?> showBookingDeadline();

    @GetMapping(BOOKING_USER)
    ResponseEntity<?> getBookingByUser(@PathVariable UUID Id);

    @GetMapping(BOOKING_BOOK)
    ResponseEntity<?> getBookingByBook(@PathVariable UUID Id);

    @PostMapping(LIBRARY)
    ResponseEntity<?> addLibrary(@RequestParam String name);
    @GetMapping(LIBRARY_ID)
    ResponseEntity<?> getLibrary(@PathVariable Integer Id);
    @GetMapping(LIBRARY)
    ResponseEntity<?> getLibraries();
    @PutMapping(LIBRARY_FLOOR)
    ResponseEntity<?> addLibraryFloor(@PathVariable Integer Id,
                                      @RequestParam Integer count);
    @PutMapping(LIBRARY_CASE)
    ResponseEntity<?> addLibraryCase(@PathVariable Integer Id,
                                     @RequestParam Integer count);

}
