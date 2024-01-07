package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@EnableScheduling
@RequiredArgsConstructor
public class BaseService {

    public synchronized User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            throw new ConflictException("Some wrong");
        User user = (User) authentication.getPrincipal();
        if (user.isDeleted()) throw new BadRequestException("Profile not found");
        return user;
    }

}
