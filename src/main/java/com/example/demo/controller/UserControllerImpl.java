package com.example.demo.controller;

import com.example.demo.service.ModerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.payload.PasswordDTO;
import com.example.demo.payload.ResultMessage;
import com.example.demo.payload.UserDTO;
import com.example.demo.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "user-controller", description = "User controller management")
@SecurityRequirement(name = "bearerAuth")
public class UserControllerImpl implements UserController {

    static final String PROFILE_INFO = "/me";
    static final String EDIT_PROFILE_INFO = "/editProfile";
    static final String DELETE_PROFILE = "/deleteProfile";
    static final String CHANGE_PASSWORD = "/change_password";
    static final String BRON_BOOK = "/bron_book/{Id}";
    static final String BOOKS = "/book";
    static final String BOOK = "/book/{Id}";
    static final String BRON_HISTORY = "/bron_history";
    static final String BRON_OLD_HISTORY = "/old_bron_history";


    private final UserService userService;
    private final ModerService moderService;

    @Override
    public ResponseEntity<?> bronBook(UUID Id, Integer count) {
        ResultMessage resultMessage = userService.bronBook(Id, count);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.ACCEPTED
                        : HttpStatus.CONFLICT)
                .body(resultMessage);
    }

    @Override
    public ResponseEntity<?> getBooks() {
        ResultMessage result = moderService.getBooks();
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> getBook(UUID Id) {
        ResultMessage result = moderService.getBooks(Id);
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> showBronHistory() {
        ResultMessage result = userService.showBronHistory();
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> showOldBronHistory() {
        ResultMessage result = userService.showOldBronHistory();
        return ResponseEntity.ok(result.getObject());
    }

    @Override
    public ResponseEntity<?> showProfile() {
        return ResponseEntity.ok(userService.showProfile());
    }

    @Override
    public ResponseEntity<?> editProfile(UserDTO userDTO) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userService.editProfile(userDTO));
    }

    @Override
    public ResponseEntity<?> deleteProfile() {
        return ResponseEntity.ok(userService.deleteProfile());
    }

    @Override
    public ResponseEntity<?> changePassword(PasswordDTO passwordDTO) {
        ResultMessage resultMessage = userService.changePassword(passwordDTO);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.ACCEPTED
                        : HttpStatus.NOT_ACCEPTABLE)
                .body(resultMessage);
    }


}
