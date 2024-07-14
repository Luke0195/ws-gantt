package br.com.plannermanager.controllers.exception;


import br.com.plannermanager.bunisses.exceptions.EntityAlreadyExistsException;
import br.com.plannermanager.dto.response.StandardErrorPayload;
import br.com.plannermanager.utils.http.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ProjectManagerExceptionHandler {

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<StandardErrorPayload> entityAlreadyExists(HttpServletRequest httpServletRequest, EntityAlreadyExistsException exception){
    String pathUrl = HttpUtil.getUriFromRequest(httpServletRequest);
    int badRequestStatusCode = HttpStatus.BAD_REQUEST.value();
    StandardErrorPayload responseData = StandardErrorPayload
              .builder()
            .error("Entity already exists!")
            .exceptionMessage(exception.getMessage())
            .path(pathUrl)
            .status(badRequestStatusCode)
            .timestamp(LocalDateTime.now())
            .fieldError(new ArrayList<>())
            .build();
    return ResponseEntity.status(badRequestStatusCode).body(responseData);
  }



}
