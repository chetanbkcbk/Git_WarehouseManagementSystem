package com.jsp.wms.requestdto;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.enums.AdminType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRequest {

	@NotNull(message="username cannot be null")
	@NotBlank(message="username cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Name should only contain alphabetic characters")

	private String name;
	
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should end with @gmail.com")	@NotNull(message="email cannot be null")
	@NotBlank(message="email cannot be null")
	
	private String email;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", 
			message = "Password must contain at least one letter, one number, one special character, and be at least 8 characters long")
	private String password ;
	
	
}
