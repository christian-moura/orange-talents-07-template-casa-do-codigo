package br.com.zup.casadocodigo.handler;

import br.com.zup.casadocodigo.handler.exception.PersonalizadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> MethodArgumentNotValid(MethodArgumentNotValidException e){
        List<Error> erros = e.getFieldErrors().stream().map(Error::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Error> IllegalState(IllegalStateException e){
        Error error = new Error(e.getMessage(),"id" );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(PersonalizadaException.class)
    public ResponseEntity<Error> CidadeIndisponivelParaOPais(PersonalizadaException e){
        Error error = new Error(e.getMessage(),e.getCampo() );
        return ResponseEntity.badRequest().body(error);
    }
}