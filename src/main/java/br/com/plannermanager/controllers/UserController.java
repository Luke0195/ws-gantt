package br.com.plannermanager.controllers;

import br.com.plannermanager.bunisses.service.impl.UserServiceImpl;
import br.com.plannermanager.dto.request.UserRequestDto;
import br.com.plannermanager.dto.response.UserResponseDto;
import br.com.plannermanager.utils.http.HttpUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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


}
