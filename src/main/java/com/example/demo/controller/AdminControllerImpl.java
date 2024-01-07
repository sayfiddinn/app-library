package com.example.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.payload.ResultMessage;
import com.example.demo.service.AdminService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "admin-controller", description = "Admin controller management, admin and above can access")
@SecurityRequirement(name = "bearerAuth")
public class AdminControllerImpl implements AdminController {
    static final String MODERATOR = "/moderator";
    static final String ADD_MODERATOR = MODERATOR + "/{user_id}";
    static final String DELETE_MODERATOR = MODERATOR + "/{id}";
    static final String SHOW_MODERATORS = MODERATOR;
    static final String SHOW_MODERATOR = MODERATOR + "/{id}";
    static final String SHOW_USERS = "/users";
    static final String SHOW_USER = SHOW_USERS + "/{user_id}";



    private final AdminService adminService;



    @Override
    public ResponseEntity<?> addModerator(UUID userId) {
        ResultMessage resultMessage = adminService.addModerator(userId);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.CREATED
                        : HttpStatus.NOT_ACCEPTABLE)
                .body(resultMessage);
    }

    @Override
    public ResponseEntity<?> deleteModerator(UUID id) {
        ResultMessage resultMessage = adminService.deleteModerator(id);
        return ResponseEntity
                .status(resultMessage.getSuccess()
                        ? HttpStatus.OK
                        : HttpStatus.NOT_FOUND)
                .body(resultMessage);
    }

    @Override
    public ResponseEntity<?> showModerators(Integer page, Integer size) {
        return ResponseEntity.ok(adminService
                .showModerators(page, size)
                .getObject());
    }

    @Override
    public ResponseEntity<?> showModerator(UUID id) {
        return ResponseEntity.ok(adminService
                .showModerator(id)
                .getObject());
    }


    @Override
    public ResponseEntity<?> showUsers(Integer page, Integer size) {
        return ResponseEntity.ok(adminService
                .showUsers(page, size)
                .getObject());
    }

    @Override
    public ResponseEntity<?> showUser(UUID user_id) {
        return ResponseEntity.ok(adminService
                .showUser(user_id)
                .getObject());
    }

}
