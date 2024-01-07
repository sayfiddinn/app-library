package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Library;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.MyMapper;
import com.example.demo.payload.BookDTO;
import com.example.demo.payload.ResultMessage;
import com.example.demo.repository.BookRepo;
import com.example.demo.repository.BookingRepo;
import com.example.demo.repository.LibraryRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModerServiceImpl implements ModerService {
    private final LibraryRepo libraryRepo;
    private final BookRepo bookRepo;
    private final BookingRepo bookingRepo;
    private final UserRepository userRepository;
    private final MyMapper mapper;

    @Override
    public ResultMessage addBook(BookDTO bookDTO) {
        Library library = libraryRepo.findById(bookDTO.getLibraryId())
                .orElseThrow(() -> new NotFoundException("Library Id not found"));
        if (Validation.checkBookCount(library.getMaxCount() - library.getBookCount(),
                bookDTO.getCount())) {
            return new ResultMessage(false, "Kutubxonaga buncha kitob sig'maydi");
        }
        Book book = new Book();
        book.setLibrary(library);
        mapper.mappingBook(bookDTO, book);
        library.setBookCount(library.getBookCount() + bookDTO.getCount());
        libraryRepo.save(library);
        bookRepo.save(book);
        return new ResultMessage(true, "Book success saved !");
    }

    @Override
    public ResultMessage addBookCount(Integer count, UUID Id) {
        Book book = getBook(Id);
        Library library = book.getLibrary();
        if (Validation.checkBookCount(library.getMaxCount() - library.getBookCount(), count)) {
            return new ResultMessage(false, "Kutubxonaga buncha kitob sig'maydi");
        }
        book.setCount(book.getCount() + count);
        library.setBookCount(library.getBookCount() + count);
        bookRepo.save(book);
        libraryRepo.save(library);
        return new ResultMessage(true, "Book success added !");
    }

    private Book getBook(UUID id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Book Id not found"));
    }

    @Override
    public ResultMessage updateBook(String name, String author, UUID Id) {
        Book book = getBook(Id);
        book.setName(name);
        book.setAuthor(author);
        bookRepo.save(book);
        return new ResultMessage(true, "Book success updated !");
    }

    @Override
    public ResultMessage getBooks() {
        List<Book> all = bookRepo.findAll();
        return new ResultMessage(true, all);
    }

    @Override
    public ResultMessage getBooks(UUID Id) {
        return new ResultMessage(true, getBook(Id));
    }

    @Override
    public ResultMessage getBookByName(String name) {
        return new ResultMessage(true, bookRepo.booksByName(name));
    }

    @Override
    public ResultMessage getBookByAuthor(String author) {
        return new ResultMessage(true, bookRepo.booksByAuthor(author));
    }

    @Override
    public ResultMessage confirmBooking(UUID id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("This order Id not found"));
        if (booking.isSuccess()) {
            return new ResultMessage(false, "This order already confirmed");
        }
        booking.setSuccess(true);
        bookingRepo.save(booking);
        return new ResultMessage(true, "This order success confirmed !");
    }

    @Override
    public ResultMessage getBooking() {
        List<Booking> all = bookingRepo.findAll();
        return new ResultMessage(true, all);
    }

    @Override
    public ResultMessage showBookingDeadline() {
        List<Booking> bookings = bookingRepo.deadlineDateBron(LocalDate.now());
        return new ResultMessage(true, bookings);
    }

    @Override
    public ResultMessage showBookingByUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User Id not found"));
        List<Booking> list = bookingRepo.findByUser(user);
        return new ResultMessage(true, list);
    }

    @Override
    public ResultMessage showBookingByBook(UUID id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Book Id not found"));
        List<Booking> list = bookingRepo.findByBook(book);
        return new ResultMessage(true, list);
    }

    @Override
    public ResultMessage addLibrary(String name) {
        if (libraryRepo.existsByName(name)) {
            return new ResultMessage(false, "Already exist library with this name");
        }
        Library library = new Library();
        library.setName(name);
        libraryRepo.save(library);
        return new ResultMessage(true, "Library saved successfully");
    }

    @Override
    public ResultMessage getLibrary(Integer id) {
        return new ResultMessage(true, library(id));
    }

    @Override
    public ResultMessage getLibraries() {
        List<Library> all = libraryRepo.findAll();
        return new ResultMessage(true, all);
    }

    @Override
    public ResultMessage addLibraryFloor(Integer id, Integer count) {
        Validation.checkCount(count);
        Library library = library(id);
        library.setFloorCount(library.getFloorCount() + count);
        library.setCaseCount(library.getFloorCount() * 20);
        library.setShelfCount(library.getCaseCount() * 10);
        library.setMaxCount(library.getShelfCount() * 20);
        return new ResultMessage(true, "Library floor success added");
    }

    @Override
    public ResultMessage addLibraryCase(Integer id, Integer count) {
        Validation.checkCount(count);
        Library library = library(id);
        library.setCaseCount(library.getCaseCount() + count);
        library.setShelfCount(library.getCaseCount() * 10);
        library.setMaxCount(library.getShelfCount() * 20);
        return new ResultMessage(true, "Library case success added");
    }

    private Library library(Integer id) {
        return libraryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("This library Id not found"));
    }
}
