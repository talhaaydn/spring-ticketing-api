package com.talha.ticketing.service;

import com.talha.ticketing.dto.user.UserRequestDTO;
import com.talha.ticketing.dto.user.UserResponseDTO;
import com.talha.ticketing.entity.User;
import com.talha.ticketing.mapper.UserMapper;
import com.talha.ticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDTO> getAllUsers() {
        return userMapper.toResponseDTOList(userRepository.findAll());
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + userRequestDTO.getEmail());
        }

        User user = userMapper.toEntity(userRequestDTO);

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDTO(savedUser);
    }
}

