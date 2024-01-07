package com.example.demo.util;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ConflictException;

public class Validation {

    public static void checkPage(Integer page, Integer size, Long count) {
        if (page == null || page < 0) {
            throw new BadRequestException("Invalid page number");
        }
        if (size == null || size < 1) {
            throw new BadRequestException("Invalid page size");
        }
        int totalPages = (int) Math.ceil((double) count / size);
        if (totalPages == 0) {
            throw new BadRequestException("Objects not found");
        }
        if (page >= totalPages) {
            throw new BadRequestException("Invalid page number, max number of pages using this " +
                    "size(" + size + ") is equals to " + totalPages);
        }
    }


    public static boolean checkBookCount(int limit, Integer count) {
        return count > limit;
    }

    public static void checkCount(Integer count) {
        if (count<=0){
            throw new ConflictException("Count wrong");
        }
    }
}
