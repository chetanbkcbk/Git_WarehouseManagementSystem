package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.AdminService;
import com.jsp.wms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/register")  //to create SuperAdmin the url shud be register
	public ResponseEntity<ResponseStructure<AdminResponse>>createSuperAdmin(@RequestBody AdminRequest admin){
		return adminService.createSuperAdmin(admin);
	}
	
	@PostMapping("/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>>	createAdmins(@RequestBody AdminRequest admin){
	return adminService.createAdmins(admin)
	}
}
