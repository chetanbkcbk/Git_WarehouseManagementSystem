package com.jsp.wms.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.wms.exception.IllegalOperationException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	public ResponseEntity<ErrorStructure>errorResponse
	(HttpStatus status,String message,String rootCause){
		
		return ResponseEntity
				.status(status)
				.body(new ErrorStructure()
						.setStatus(status.value())
						.setMessage(message)
						.setRootCause(rootCause));
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure>handleIllegalOperation(IllegalOperationException ex)  //ctrl+shift+o for import
	{
		return errorResponse(HttpStatus.FOUND, ex.getMessage(), "Super_Admin Already Exists");
	}
	
	
	
	
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<Map<String,String>>> handleMethodArguementNotValid(MethodArgumentNotValidException ex)
	{
		List<ObjectError> objecterrors = ex.getAllErrors();  //ObjectError is parent clas
		
Map<String,String> allErrors = new HashMap<>();
		
		objecterrors.forEach(error->{               //im iterating over each errorobject from the list of error object 
			FieldError fieldError = (FieldError)error; //downcasting each 
			String field = fieldError.getField(); 
			String message = fieldError.getDefaultMessage();
/*to send many of the possible error in a 'key value pair'so that user can know 
 * 'for which field what error' i ,crete HashMap object and then using
  its ref, invoke put (key ,value)and then pass that ref to the rootcause of
  error structure,since that ref is a map ,the return type of ErrorStructure
  also shud be <Map<String,String>>>   */
			allErrors.put(field,message);
		}  );
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorStructure<Map<String,String>>()
						.setStatus(HttpStatus.BAD_REQUEST.value())
						.setMessage("Invalid input")
						.setRootCause(allErrors));
						
	}
	

	
}
