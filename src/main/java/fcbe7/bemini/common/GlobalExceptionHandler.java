package fcbe7.bemini.common;

import fcbe7.bemini.common.response.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Message> duplicateEmail(DataIntegrityViolationException e) {
        return ResponseEntity.badRequest().body(new Message("중복되는 이메일이 존재합니다"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Message> validateExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getAllErrors().get(0)
                .getDefaultMessage();
        return ResponseEntity.badRequest().body(new Message(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message> exceptionHandler(Exception e) {
        log.error("Exception : {}", e.getClass().getName());
        return ResponseEntity.badRequest().body(new Message(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Message> runtimeException(RuntimeException e){
        return ResponseEntity.badRequest().body(new Message(e.getMessage()));
    }
}
