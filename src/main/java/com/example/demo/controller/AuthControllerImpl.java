package com.example.demo.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.payload.ResultMessage;
import com.example.demo.payload.SignDTO;
import com.example.demo.payload.SignUpDTO;
import com.example.demo.service.AuthService;

@RestController
@RequiredArgsConstructor
@Tag(name = "auth-controller", description = "Auth controller management")
public class AuthControllerImpl implements AuthController {
    static final String SIGN_UP = "/signUp";
    static final String SIGN_IN = "/signIn";
    private final AuthService authService;

    @Override
    public ResponseEntity<?> signUp(@Valid SignUpDTO signUpDTO) {
        ResultMessage resultMessage = authService.signUp(signUpDTO);
        return ResponseEntity.status(
                resultMessage.getSuccess() ?
                        HttpStatus.OK :
                        HttpStatus.CONFLICT
        ).body(resultMessage);
    }


    @Override
    public ResponseEntity<?> signIn(SignDTO signDTO) {
        ResultMessage resultMessage = authService.signIn(signDTO);
        return ResponseEntity.status(
                resultMessage.getSuccess() ?
                        HttpStatus.ACCEPTED :
                        HttpStatus.CONFLICT
        ).body(resultMessage);
    }

}
