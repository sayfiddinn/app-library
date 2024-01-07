package com.example.demo.controller;

import com.example.demo.payload.BookDTO;
import com.example.demo.payload.ResultMessage;
import com.example.demo.service.ModerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "moder-controller", description = "Moder controller management, moderator and above can access")
@SecurityRequirement(name = "bearerAuth")
public class ModerControllerImpl implements ModerController {
    static final String LIBRARY_FLOOR = "library_floor/{Id}";
    static final String LIBRARY_CASE = "library_case/{Id}";
    static final String BOOK = "/book";
    static final String ID = "/{Id}";
    static final String CONFIRM_BOOKING = "/confirm_booking/{Id}";
    static final String BOOKING = "/booking_history";
    static final String BOOKING_USER = "/booking_by_user/{Id}";
    static final String BOOKING_BOOK = "/booking_by_book/{Id}";
    static final String BOOKING_DEADLINE = "/deadline_booking_history";
    static final String BOOK_ID = BOOK + ID;
    static final String SEARCH_NAME = BOOK + "_name";
    static final String SEARCH_AUTHOR = BOOK + "_author";
    static final String LIBRARY = "/library";
    static final String LIBRARY_ID = "/library/{Id}";

    private final ModerService moderService;

    @Override
    public ResponseEntity<?> addBook(BookDTO bookDTO) {
        ResultMessage resultMessage = moderService.addBook(bookDTO);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.CREATED
                        : HttpStatus.CONFLICT)
                .body(resultMessage);
    }

    @Override
    public ResponseEntity<?> addBookCount(Integer count, UUID Id) {
        ResultMessage resultMessage = moderService.addBookCount(count, Id);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.ACCEPTED
                        : HttpStatus.NOT_ACCEPTABLE)
                .body(resultMessage);
    }

    @Override
    public ResponseEntity<?> updateBook(String name, String author, UUID Id) {
        ResultMessage resultMessage = moderService.updateBook(name, author, Id);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.ACCEPTED
                        : HttpStatus.NOT_ACCEPTABLE)
                .body(resultMessage);
    }

    @Override
    public ResponseEntity<?> getBooks() {
        ResultMessage result = moderService.getBooks();
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> getBooks(UUID Id) {
        ResultMessage result = moderService.getBooks(Id);
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> getBookByName(String name) {
        ResultMessage result = moderService.getBookByName(name);
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> getBookByAuthor(String author) {
        ResultMessage result = moderService.getBookByAuthor(author);
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> confirmBooking(UUID Id) {
        ResultMessage resultMessage = moderService.confirmBooking(Id);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.ACCEPTED
                        : HttpStatus.NOT_MODIFIED)
                .body(resultMessage);
    }

    @Override
    public ResponseEntity<?> getBooking() {
        ResultMessage result = moderService.getBooking();
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> showBookingDeadline() {
        ResultMessage result = moderService.showBookingDeadline();
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> getBookingByUser(UUID Id) {
        ResultMessage result = moderService.showBookingByUser(Id);
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> getBookingByBook(UUID Id) {
        ResultMessage result = moderService.showBookingByBook(Id);
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> addLibrary(String name) {
        ResultMessage resultMessage = moderService.addLibrary(name);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.CREATED
                        : HttpStatus.CONFLICT)
                .body(resultMessage);
    }

    @Override
    public ResponseEntity<?> getLibrary(Integer Id) {
        ResultMessage result = moderService.getLibrary(Id);
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> getLibraries() {
        ResultMessage result = moderService.getLibraries();
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> addLibraryFloor(Integer Id,Integer count) {
        return ResponseEntity.ok(moderService.addLibraryFloor(Id,count));
    }

    @Override
    public ResponseEntity<?> addLibraryCase(Integer Id, Integer count) {
        return ResponseEntity.ok(moderService.addLibraryCase(Id,count));
    }
}
