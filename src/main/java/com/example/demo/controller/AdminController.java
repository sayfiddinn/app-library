package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.util.Path;

import java.util.UUID;

import static com.example.demo.controller.AdminControllerImpl.*;

@RequestMapping(Path.BASE_PATH_ADMIN)
@PreAuthorize("hasAnyAuthority('ADD_MODERATOR','DELETE_MODERATOR','SHOW_MODERATOR','SHOW_SOLD_TICKET_DETAIL','SHOW_USER')")
public interface AdminController {

    @PostMapping(ADD_MODERATOR)
    ResponseEntity<?> addModerator(@PathVariable(name = "user_id") UUID userId);

    @DeleteMapping(DELETE_MODERATOR)
    ResponseEntity<?> deleteModerator(@PathVariable UUID id);

    @GetMapping(SHOW_MODERATORS)
    ResponseEntity<?> showModerators(@RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer size);

    @GetMapping(SHOW_MODERATOR)
    ResponseEntity<?> showModerator(@PathVariable UUID id);


    @GetMapping(SHOW_USERS)
    ResponseEntity<?> showUsers(@RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "10") Integer size);

    @GetMapping(SHOW_USER)
    ResponseEntity<?> showUser(@PathVariable UUID user_id);

}
