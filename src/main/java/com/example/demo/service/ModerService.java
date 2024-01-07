package com.example.demo.service;

import com.example.demo.payload.BookDTO;
import com.example.demo.payload.ResultMessage;


import java.util.UUID;


public interface ModerService {
    ResultMessage addBook(BookDTO bookDTO);

    ResultMessage addBookCount(Integer count,
                               UUID Id);

    ResultMessage updateBook(String name,
                             String author,
                             UUID Id);

    ResultMessage getBooks();

    ResultMessage getBooks(UUID Id);

    ResultMessage getBookByName(String name);

    ResultMessage getBookByAuthor(String author);

    ResultMessage confirmBooking(UUID id);

    ResultMessage getBooking();

    ResultMessage showBookingDeadline();

    ResultMessage showBookingByUser(UUID id);

    ResultMessage showBookingByBook(UUID id);

    ResultMessage addLibrary(String name);

    ResultMessage getLibrary(Integer id);

    ResultMessage getLibraries();

    ResultMessage addLibraryFloor(Integer id, Integer count);

    ResultMessage addLibraryCase(Integer id, Integer count);
}
