package br.com.plannermanager.controllers;

import br.com.plannermanager.bunisses.service.impl.UserServiceImpl;
import br.com.plannermanager.dto.request.UserRequestDto;
import br.com.plannermanager.dto.response.UserResponseDto;
import br.com.plannermanager.utils.http.HttpUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

   private final UserServiceImpl userService;

   @PostMapping
   public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto requestDto){
      UserResponseDto payload = userService.createUser(requestDto);
      URI uri = HttpUtil.getUriFromObject(payload.getId());
      return HttpUtil.getCreatedResponse(uri, payload);
   }

   @GetMapping
   public ResponseEntity<Page<UserResponseDto>> findAllPaged(
           @RequestParam(value = "page", defaultValue = "0") Integer page,
           @RequestParam(value="linesPerPage", defaultValue = "12") Integer linesPerPage,
           @RequestParam(value="orderBy", defaultValue = "name") String orderBy,
           @RequestParam(value="direction", defaultValue = "ASC") String direction
   ){
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
      Page<UserResponseDto> responseData = userService.findAllUsersPaged(pageRequest);
      return HttpUtil.getSuccessResponse(responseData);
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<UserResponseDto> findUseById(@PathVariable UUID id){
      UserResponseDto userResponseDto = userService.findUserById(id);
      return HttpUtil.getSuccessResponse(userResponseDto);
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Void> deleteById(@PathVariable UUID id){
      userService.deleteById(id);
      return ResponseEntity.noContent().build();
   };

}
