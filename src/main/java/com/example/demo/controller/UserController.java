package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.payload.PasswordDTO;
import com.example.demo.payload.UserDTO;
import com.example.demo.util.Path;

import java.util.UUID;

import static com.example.demo.controller.UserControllerImpl.*;

@PreAuthorize("hasAnyAuthority('SHOW_PROFILE','EDITE_PROFILE','DELETE_PROFILE','SHOW_FLIGHT_HISTORY','SHOW_TICKET_HISTORY')")
@RequestMapping(Path.BASE_PATH_USER)
public interface UserController {
    @PostMapping(BRON_BOOK)
    ResponseEntity<?> bronBook(@PathVariable UUID Id, @RequestParam Integer count);

    @GetMapping(BOOKS)
    ResponseEntity<?> getBooks();

    @GetMapping(BOOK)
    ResponseEntity<?> getBook(@PathVariable UUID Id);

    @GetMapping(BRON_HISTORY)
    ResponseEntity<?> showBronHistory();

    @GetMapping(BRON_OLD_HISTORY)
    ResponseEntity<?> showOldBronHistory();

    @GetMapping(PROFILE_INFO)
    ResponseEntity<?> showProfile();

    @PutMapping(EDIT_PROFILE_INFO)
    ResponseEntity<?> editProfile(@RequestBody UserDTO userDTO);

    @DeleteMapping(DELETE_PROFILE)
    ResponseEntity<?> deleteProfile();

    @PostMapping(CHANGE_PASSWORD)
    ResponseEntity<?> changePassword(@RequestBody PasswordDTO passwordDTO);


}
