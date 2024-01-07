package com.example.demo.mapper;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.payload.RoleDTO;
import com.example.demo.payload.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.example.demo.entity.*;
import com.example.demo.payload.*;

@Mapper(componentModel = "spring")
public interface MyMapper {
    MyMapper INSTANCE = Mappers.getMapper(MyMapper.class);

    void userDtoToUser(UserDTO userDTO, @MappingTarget User user);

    void mappingRole(RoleDTO roleDTO, @MappingTarget Role role);

    void mappingBook(BookDTO bookDTO, @MappingTarget Book book);

}
