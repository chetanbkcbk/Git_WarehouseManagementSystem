package com.jsp.wms.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AddressRequest {

private String addressLine;
	
@NotNull(message="city cannot be null")
@NotBlank(message="city cannot be blank")
	private String city;
	
@NotNull(message="state cannot be null")
@NotBlank(message="state cannot be blank")
	private String state; 

@NotNull(message="country cannot be null")
@NotBlank(message="country cannot be blank")
	private String country;

@NotNull(message="pincode cannot be null")
@NotBlank(message="pincode cannot be blank")
	private String pincode;
	
	private String longitude;

	private String latitude;
	
}
