package com.jsp.wms.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
@Component
public class AdminMapper {

	@Autowired
	PasswordEncoder passwordEncoder;  //autowire the PasswordEncoder and using it invoke its method
	
public Admin mapToAdmin(AdminRequest adminRequest,Admin admin) {
		
	admin.setName(adminRequest.getName());
	admin.setEmail(adminRequest.getEmail());
	admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));//used to encrpt the password into the database or else we get Invalid User credentials
		return admin;
	}
	
	public AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder()
				.adminId(admin.getAdminId())
				.name(admin.getName())
				.email(admin.getEmail())	
				.build();
	}	
}
