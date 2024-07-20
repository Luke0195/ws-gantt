package br.com.plannermanager.controllers.exception;


import br.com.plannermanager.bunisses.exceptions.EntityAlreadyExistsException;
import br.com.plannermanager.bunisses.exceptions.EntityNotExistsException;
import br.com.plannermanager.bunisses.exceptions.InvalidParamException;
import br.com.plannermanager.dto.response.FieldErrorDto;
import br.com.plannermanager.dto.response.StandardErrorDto;
import br.com.plannermanager.utils.http.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    int badRequestStatusCode = HttpUtil.getStatusCode(HttpStatus.BAD_REQUEST);
    StandardErrorDto responseData = makeStandardErrorPayload("Entity already Exists!",
            getExceptionMessage(exception),pathUrl, badRequestStatusCode, new ArrayList<>());
    return HttpUtil.getBadRequestResponse(responseData);
  }

  @ExceptionHandler(InvalidParamException.class)
  public ResponseEntity<StandardErrorDto> invalidParamException(HttpServletRequest httpServletRequest, InvalidParamException exception){
    String pathUrl = HttpUtil.getUriFromRequest(httpServletRequest);
    int badRequestStatusCode = HttpUtil.getStatusCode(HttpStatus.BAD_REQUEST);
    StandardErrorDto responseData = makeStandardErrorPayload("Invalid param!", getExceptionMessage(exception), pathUrl, badRequestStatusCode, new ArrayList<>());
    return HttpUtil.getBadRequestResponse(responseData);

  }

  @ExceptionHandler(EntityNotExistsException.class)
  public ResponseEntity<StandardErrorDto> entityNotFound(HttpServletRequest httpServletRequest, EntityNotExistsException exception){
    String pathUrl = HttpUtil.getUriFromRequest(httpServletRequest);
    int notFoundStatus = HttpUtil.getStatusCode(HttpStatus.NOT_FOUND);
    StandardErrorDto responseData = makeStandardErrorPayload("Entity not found!", getExceptionMessage(exception), pathUrl,notFoundStatus, new ArrayList<>());
    return HttpUtil.getNotFoundResponse(responseData);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardErrorDto> fieldValidationError(HttpServletRequest httpServletRequest, MethodArgumentNotValidException exception){
    List<FieldErrorDto> fieldErrors = extractFieldsErrorsFromValidation(exception);
    String pathUrl = HttpUtil.getUriFromRequest(httpServletRequest);
    int badRequest = HttpUtil.getStatusCode(HttpStatus.BAD_REQUEST);
    StandardErrorDto responseData = makeStandardErrorPayload("Validation Fails",
            "Invalid data is provided, see field_errors to validate the payload", pathUrl,
            badRequest, fieldErrors);
    return HttpUtil.getBadRequestResponse(responseData);
  }

  private static List<FieldErrorDto> extractFieldsErrorsFromValidation(MethodArgumentNotValidException validationException){
    List<FieldErrorDto> fieldErrorDto = new ArrayList<>();
    validationException.getFieldErrors().forEach(x -> {
      String fieldName = x.getField();
      String fieldMessage = x.getDefaultMessage();
      fieldErrorDto.add(new FieldErrorDto(fieldName, fieldMessage));
    });
    return fieldErrorDto;
  }

  private static StandardErrorDto makeStandardErrorPayload(String error, String exceptionMessage, String path, int statusCode, List<FieldErrorDto> errors){
     return StandardErrorDto
             .builder()
             .error(error)
             .exceptionMessage(exceptionMessage)
             .path(path)
             .status(statusCode)
             .timestamp(LocalDateTime.now())
             .fieldError(errors)
             .build();
  }

  private static String getExceptionMessage(RuntimeException exception){
    return exception.getMessage();
  }

}
