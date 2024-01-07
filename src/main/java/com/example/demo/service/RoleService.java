package com.example.demo.service;

import com.example.demo.payload.ResultMessage;
import com.example.demo.payload.RoleDTO;

import java.util.UUID;


public interface RoleService {
    ResultMessage getRoles();

    ResultMessage getRole(Integer id);

    ResultMessage addRole(RoleDTO roleDTO);

    ResultMessage updateRole(RoleDTO roleDTO, Integer id);

    ResultMessage deleteRole(Integer id);

    ResultMessage changeRole(UUID userId, Integer roleId);
}
