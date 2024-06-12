package com.jsp.wms.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.wms.exception.AdminNotFoundByEmailException;
import com.jsp.wms.exception.AdminNotFoundByIdException;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	public ResponseEntity <ErrorStructure> errorResponse(HttpStatus status,String message,String rootCause)
	{
		
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
	{																					//is a predefined Exception,hence no need of creating a exception just handle it
		List<ObjectError> objecterrors = ex.getAllErrors();  //ObjectError is parent clas
		
Map<String,String> allErrors = new HashMap<>();
					//forEach() takes Consumer as parameter
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
	
	@ExceptionHandler
public ResponseEntity<ErrorStructure> handleUsernameNotFound(UsernameNotFoundException ex)
															//is a predefined exception 
{
	return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Username Not Found in the Database");
}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleWarehouseNotFoundById(WarehouseNotFoundByIdException ex)
	//is a custom exception 
{
return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Warehouse Not Found in the Database");
}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleAdminNotFoundByEmail(AdminNotFoundByEmailException ex)
	//is a custom exception 
{
return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Admin with such email Not Found in the Database");
}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleAdminNotFoundById(AdminNotFoundByIdException ex)
	//is a custom exception 
{
return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "Admin with such ID Not Found in the Database");
}
}
