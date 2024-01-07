package com.example.demo.service;

import com.example.demo.payload.ResultMessage;
import com.example.demo.payload.SignDTO;
import com.example.demo.payload.SignUpDTO;


public interface AuthService {
    ResultMessage signUp(SignUpDTO signUpDTO);

    ResultMessage signIn(SignDTO signDTO);

}
