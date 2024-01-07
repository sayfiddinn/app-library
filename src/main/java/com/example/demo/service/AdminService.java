package com.example.demo.service;

import com.example.demo.payload.ResultMessage;


import java.util.UUID;

public interface AdminService {
    ResultMessage addModerator(UUID userId);

    ResultMessage deleteModerator(UUID id);

    ResultMessage showModerators(Integer page, Integer size);

    ResultMessage showModerator(UUID id);

    ResultMessage showUsers(Integer page, Integer size);

    ResultMessage showUser(UUID user_id);

}
