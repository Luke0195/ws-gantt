package br.com.plannermanager.controllers;

import br.com.plannermanager.bunisses.service.impl.GroupServiceImpl;
import br.com.plannermanager.dto.request.GroupRequestDto;
import br.com.plannermanager.dto.response.GroupResponseDto;
import br.com.plannermanager.utils.http.HttpUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(value = "/groups")
@AllArgsConstructor
public class GroupController {

    private final GroupServiceImpl groupService;

    @PostMapping
    public ResponseEntity<GroupResponseDto> createGroup(@Valid @RequestBody GroupRequestDto groupRequestDto){
        GroupResponseDto response = groupService.createGroup(groupRequestDto);
        return HttpUtil.getCreatedResponse(HttpUtil.getUriFromObject(response.getId()),response);
    }

    @GetMapping
    public ResponseEntity<Page<GroupResponseDto>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC") String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<GroupResponseDto> groups = groupService.findAll(pageRequest);
        return HttpUtil.getSuccessResponse(groups);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GroupResponseDto> findById(@PathVariable UUID id){
        GroupResponseDto group = groupService.findById(id);
        return HttpUtil.getSuccessResponse(group);
    }


}
