package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.payload.SignDTO;
import com.example.demo.payload.SignUpDTO;
import com.example.demo.util.Path;


import static com.example.demo.controller.AuthControllerImpl.*;

@RequestMapping(Path.BASE_PATH_AUTH)
public interface AuthController {
    @PostMapping(SIGN_UP)
    ResponseEntity<?> signUp(@RequestBody @Valid SignUpDTO signUpDTO);

    @PostMapping(SIGN_IN)
    ResponseEntity<?> signIn(@RequestBody SignDTO signDTO);

}
