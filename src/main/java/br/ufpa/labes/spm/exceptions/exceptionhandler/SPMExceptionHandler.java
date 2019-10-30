package br.ufpa.labes.spm.exceptions.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SPMExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired private MessageSource messageSource;

  private final Logger log = LoggerFactory.getLogger(SPMExceptionHandler.class);

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<Error> errors = getErrorList(ex.getBindingResult());
    return handleExceptionInternal(ex, errors, headers, status, request);
  }

  @ExceptionHandler({ DataIntegrityViolationException.class })
  protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
    String userMessage =
        messageSource.getMessage("resource.must-be-unique", null, LocaleContextHolder.getLocale());
    String devMessage = ex.getMessage();
    Error error = new Error(userMessage, devMessage);
    return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  private List<Error> getErrorList(BindingResult result) {
    return result.getFieldErrors().stream()
      .map(field ->
        new Error(
          messageSource.getMessage(field, LocaleContextHolder.getLocale()),
          field.getField()))
      .collect(Collectors.toList());
  }

  private class Error {
    private String devMessage;
    private String userMessage;

    public Error(String userMessage, String devMessage) {
      this.devMessage = devMessage;
      this.userMessage = userMessage;
    }

    public String getDevMessage() {
      return devMessage;
    }

    public String getUserMessage() {
      return userMessage;
    }
  }
}
