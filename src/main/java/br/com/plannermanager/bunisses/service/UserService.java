package br.com.plannermanager.bunisses.service;

import br.com.plannermanager.domain.User;
import br.com.plannermanager.dto.request.UserRequestDto;
import br.com.plannermanager.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    UserResponseDto createUser(UserRequestDto requestDto);
    Page<User> findAllUsersPaged(Pageable pageable);
    User findUserById(UUID id);
    void deleteById(UUID id);
}
