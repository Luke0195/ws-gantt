package br.com.plannermanager.bunisses.service.impl;

import br.com.plannermanager.bunisses.exceptions.EntityAlreadyExistsException;
import br.com.plannermanager.bunisses.exceptions.InvalidParamException;
import br.com.plannermanager.bunisses.mapper.UserMapper;
import br.com.plannermanager.bunisses.service.UserService;
import br.com.plannermanager.domain.Group;
import br.com.plannermanager.domain.User;
import br.com.plannermanager.dto.request.UserRequestDto;
import br.com.plannermanager.dto.response.UserResponseDto;
import br.com.plannermanager.repository.GroupRepository;
import br.com.plannermanager.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
     return null;
    }

    @Override
    public Page<User> findAllUsersPaged(Pageable pageable) {
        return null;
    }

    @Override
    public User findUserById(UUID id) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
