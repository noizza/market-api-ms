package com.apimicroservice.users.repository;

import java.util.List;

import org.mapstruct.Mapper;

import com.apimicroservice.users.dto.UserResponseDTO;
import com.apimicroservice.users.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDTO(User user);
    User toEntity(UserResponseDTO userDTO);
    List<UserResponseDTO> toDTOList(List<User> users);
}
