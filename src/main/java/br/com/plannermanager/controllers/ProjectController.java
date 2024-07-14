package br.com.plannermanager.controllers;

import br.com.plannermanager.bunisses.service.impl.ProjectServiceImpl;
import br.com.plannermanager.dto.request.ProjectRequestDto;
import br.com.plannermanager.dto.response.ProjectResponseDto;
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
@RequestMapping(value = "/projects")
@AllArgsConstructor
public class ProjectController {

  private final ProjectServiceImpl projectService;

  @PostMapping
  public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto projectRequestDto){
      ProjectResponseDto response = projectService.create(projectRequestDto);
      URI uri = HttpUtil.getUriFromObject(response);
      return HttpUtil.getCreatedResponse(uri, response);
  }


}
