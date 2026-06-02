package com.apimicroservice.users.repository;

import java.util.List;

import org.mapstruct.Mapper;

import com.apimicroservice.users.dto.UserDTO;
import com.apimicroservice.users.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> toDTOList(List<User> users);
}
