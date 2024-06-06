package com.jsp.wms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.enums.Privileges;
import com.jsp.wms.mapper.AdminMapper;
import com.jsp.wms.repository.AdminRepository;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.AdminService;
import  com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.util.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService {

	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) {
		if( adminRepository.existsByAdminType(AdminType.SUPER_ADMIN))
		{
		//since no need to find SuperAdmin ,use existsBy() which returms boolean 
		
			throw new IllegalOperationException("The SuperAdmin Already Exists,cannot add another SuperAdmin");
		}
		else {
Admin admin = adminRepository.save(adminMapper.mapToAdmin(adminRequest, new Admin()));					
		admin.setAdminType(AdminType.SUPER_ADMIN);
		admin=adminRepository.save(admin);
			return	ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Super Admin Created")
				.setData(adminMapper.mapToAdminResponse(admin)));
		}
	
	}

	
}
