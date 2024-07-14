package br.com.plannermanager.controllers.exception;


import br.com.plannermanager.bunisses.exceptions.EntityAlreadyExistsException;
import br.com.plannermanager.dto.response.StandardErrorDto;
import br.com.plannermanager.utils.http.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ProjectManagerExceptionHandler {

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<StandardErrorDto> entityAlreadyExists(HttpServletRequest httpServletRequest, EntityAlreadyExistsException exception){
    String pathUrl = HttpUtil.getUriFromRequest(httpServletRequest);
    int badRequestStatusCode = HttpStatus.BAD_REQUEST.value();
    StandardErrorDto responseData = makeStandardErrorPayload("Entity already Exists!", getExceptionMessage(exception),pathUrl, badRequestStatusCode, new ArrayList<>());
    return HttpUtil.getBadRequestResponse(responseData);
  }

  private static StandardErrorDto makeStandardErrorPayload(String error, String exceptionMessage, String path, int statuCode, List<Object> errors){
     return StandardErrorDto
             .builder()
             .error(error)
             .exceptionMessage(exceptionMessage)
             .path(path)
             .status(statuCode)
             .timestamp(LocalDateTime.now())
             .fieldError(errors)
             .build();
  }

  private static String getExceptionMessage(RuntimeException exception){
    return exception.getMessage();
  }

}
