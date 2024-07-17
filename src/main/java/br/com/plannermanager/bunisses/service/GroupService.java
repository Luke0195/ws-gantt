package br.com.plannermanager.bunisses.service;

import br.com.plannermanager.dto.request.GroupRequestDto;
import br.com.plannermanager.dto.response.GroupResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface GroupService {

    GroupResponseDto createGroup(GroupRequestDto groupRequestDto);
    Page<GroupResponseDto> findAll(PageRequest pageRequest);
    GroupResponseDto findById(UUID id);
}
