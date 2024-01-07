package com.example.demo.service;

import com.example.demo.payload.PasswordDTO;
import com.example.demo.payload.ResultMessage;
import com.example.demo.payload.UserDTO;

import java.util.UUID;


public interface UserService {
    ResultMessage showProfile();

    ResultMessage editProfile(UserDTO userDTO);

    ResultMessage deleteProfile();


    ResultMessage changePassword(PasswordDTO passwordDTO);

    ResultMessage bronBook(UUID id, Integer count);

    ResultMessage showBronHistory();

    ResultMessage showOldBronHistory();
}
