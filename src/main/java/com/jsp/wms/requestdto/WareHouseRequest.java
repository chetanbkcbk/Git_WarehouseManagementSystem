package com.jsp.wms.requestdto;

import com.jsp.wms.entity.Admin;

import jakarta.persistence.OneToOne;
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
public class WareHouseRequest {

	@NotNull(message="username cannot be null")
	@NotBlank(message="username cannot be blank")
	private String name; 

	
}
