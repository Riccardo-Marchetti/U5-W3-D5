package riccardo.U5W3D5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import riccardo.U5W3D5.payloads.ErrorsDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest (BadRequestException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleNotFoundException (NotFoundException ex){
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }
}
