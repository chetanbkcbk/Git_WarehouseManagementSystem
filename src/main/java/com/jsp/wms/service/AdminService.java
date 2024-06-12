package com.jsp.wms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.util.ResponseStructure;

import jakarta.validation.Valid;

public interface AdminService {


public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest);

public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest,int wareHouseId);

public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin( AdminRequest adminRequest);

public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin( AdminRequest adminRequest,
		int adminId);

public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId);

public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAdmins();
}