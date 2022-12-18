package desafio5.demo.exceptions.globalexceptionhandler;

import desafio5.demo.exceptions.dtos.ErrorMensajeDTO;
import desafio5.demo.exceptions.kinds.ContactoExistente;
import desafio5.demo.exceptions.kinds.ContactoInexistente;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class GlobalExceptionhandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMensajeDTO> defaultErrorHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMensajeDTO(e.getMessage(),req.getRequestURI(),"Codigo interno"+" "+20),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ContactoInexistente.class)
    @ResponseBody
    public ResponseEntity<ErrorMensajeDTO> notFoundException(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMensajeDTO(e.getMessage(), req.getRequestURI(),"Codigo interno"+" "+20),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactoExistente.class)
    @ResponseBody
    public ResponseEntity<ErrorMensajeDTO> contactoFoundException(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMensajeDTO(e.getMessage(),req.getRequestURI(), "Codigo interno"+" "+20), HttpStatus.BAD_REQUEST);
    }
}
