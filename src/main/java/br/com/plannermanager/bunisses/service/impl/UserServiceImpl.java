package br.com.plannermanager.bunisses.service.impl;

import br.com.plannermanager.bunisses.exceptions.EntityAlreadyExistsException;
import br.com.plannermanager.bunisses.exceptions.EntityNotExistsException;
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
     Optional<User> findUserByName = userRepository.findUserByName(requestDto.getName());
     if(findUserByName.isPresent()) throw new EntityAlreadyExistsException("User name already exists!");
     Group group = groupRepository.findById(requestDto.getGroupId()).orElseThrow(() -> new InvalidParamException("group_id not found!"));
     User user = UserMapper.INSTANCE.mapDtoToEntity(requestDto, group);
     user = userRepository.save(user);
     return UserMapper.INSTANCE.mapEntityToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDto> findAllUsersPaged(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(UserMapper.INSTANCE::mapEntityToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findUserById(UUID id) {
       User user = userRepository.findById(id).orElseThrow(() -> new EntityNotExistsException("user id not found!"));
       return UserMapper.INSTANCE.mapEntityToDto(user);
    }

    @Override
    public void deleteById(UUID id) {
     userRepository.deleteById(id);
    }
}
