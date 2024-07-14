package br.com.plannermanager.controllers;

import br.com.plannermanager.bunisses.service.impl.ProjectServiceImpl;
import br.com.plannermanager.dto.request.ProjectRequest;
import br.com.plannermanager.dto.response.ProjectPayload;
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
  public ResponseEntity<ProjectPayload> createProject(@Valid @RequestBody ProjectRequest projectRequest){
      ProjectPayload response = projectService.create(projectRequest);
      URI uri = HttpUtil.getUriFromObject(response);
      return HttpUtil.getCreatedResponse(uri, response);
  }


}
