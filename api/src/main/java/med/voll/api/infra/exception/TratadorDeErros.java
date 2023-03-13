package med.voll.api.infra.exception;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {
	
	@ExceptionHandler (EntityNotFoundException.class) 
	public ResponseEntity tratarError404() { 
	 return ResponseEntity.notFound().build(); 
	 }
	 
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity tratarErroConstraints(DataIntegrityViolationException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarError400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidação::new).toList());
		
	}
	
	private record DadosErroValidação(String campo, String mensagem) {
		
		
		public DadosErroValidação(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
		
	}

}
