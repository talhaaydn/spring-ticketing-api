package com.talha.ticketing.mapper;

import com.talha.ticketing.dto.user.UserRequestDTO;
import com.talha.ticketing.dto.user.UserResponseDTO;
import com.talha.ticketing.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    /**
     * Entity'yi Response DTO'ya çevirir
     * @param user User entity
     * @return UserResponseDTO
     */
    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    /**
     * Request DTO'yu Entity'ye çevirir
     * @param dto UserRequestDTO
     * @return User entity
     */
    public User toEntity(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    /**
     * Entity listesini Response DTO listesine çevirir
     * @param users User entity listesi
     * @return List<UserResponseDTO>
     */
    public List<UserResponseDTO> toResponseDTOList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return Collections.emptyList();
        }

        return users.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}

