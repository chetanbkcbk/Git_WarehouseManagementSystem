package com.jsp.wms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.enums.Privileges;
import com.jsp.wms.mapper.AdminMapper;
import com.jsp.wms.repository.AdminRepository;
import com.jsp.wms.repository.WareHouseRepository;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.AdminService;
import com.jsp.wms.exception.AdminNotFoundByEmailException;
import com.jsp.wms.exception.AdminNotFoundByIdException;
import  com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.util.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService {


	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private WareHouseRepository wareHouseRepository;

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


	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest,int wareHouseId) {
/*
		Optional<WareHouse> optionalWarehouse = wareHouseRepository.findById(wareHouseId);

		if(optionalWarehouse.isPresent())
		{
			Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
			admin.setAdminType(AdminType.ADMIN);
			admin=adminRepository.save(admin);



			WareHouse warehouse = optionalWarehouse.get();
			warehouse.setAdmin(admin);

			return	ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.CREATED.value())
							.setMessage("AdminCreated")
							.setData(adminMapper.mapToAdminResponse(admin)));
		}
		else
		{
			throw new WarehouseNotFoundByIdException("Warehouse with such an ID does NOT Exist");	
		}
*/
//10th june task
	return	wareHouseRepository.findById(wareHouseId)
		.map(warehouse->{
				
		
			Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
			admin.setAdminType(AdminType.ADMIN);
			admin=adminRepository.save(admin);

			warehouse.setAdmin(admin);
            wareHouseRepository.save(warehouse);
            
            
			return	ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.CREATED.value())
							.setMessage("AdminCreated")
							.setData(adminMapper.mapToAdminResponse(admin)));	
		}).orElseThrow(()->new WarehouseNotFoundByIdException("WareHouse of this id not found")	 );
	

	}


	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest) {
	//no need of taking id(unique) from postman to fetch in order to update,coz unique data ie(email) can be fetched from authentication object of security context of security context  holder
		String email = SecurityContextHolder.getContext().getAuthentication().getName(); //will retrun string
		
	return	adminRepository.findByEmail(email).map(exadmin->
		{
		exadmin=adminMapper.mapToAdmin(adminRequest, exadmin);
		
		Admin updatedAdmin = adminRepository.save(exadmin);
		
		return	ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<AdminResponse>()
						.setStatus(HttpStatus.OK.value())
						.setMessage("Admin Updated")
						.setData(adminMapper.mapToAdminResponse(updatedAdmin)));	
	}).orElseThrow(	()->new AdminNotFoundByEmailException("Admin with such email not found") );

		}


	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest,
			int adminId) {

		return adminRepository.findById(adminId).map(exadmin->
		 {
			 exadmin=adminMapper.mapToAdmin(adminRequest, exadmin);
			 
			Admin updatedAdminBySuperAdmin =adminRepository.save(exadmin);
		
			return	ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Admin Updated ")
							.setData(adminMapper.mapToAdminResponse(updatedAdminBySuperAdmin)));	
		}).orElseThrow(	()->new AdminNotFoundByIdException("Admin with such Id not found") );		
		
	}


	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(int adminId) {

	return	adminRepository.findById(adminId).map(admin->{
			AdminResponse adminResponse = adminMapper.mapToAdminResponse(admin);
			
			return	ResponseEntity
					.status(HttpStatus.FOUND)
			.body(new ResponseStructure<AdminResponse>()
			.setStatus( HttpStatus.FOUND.value() )
			.setMessage("Admin Found")
			.setData(adminResponse) );
			
				}).orElseThrow( ()-> new AdminNotFoundByIdException("Failed to find Admin based on Id") );
				
			}


	@Override
	public ResponseEntity<ResponseStructure<List<AdminResponse>>>findAdmins() {
		
	List<AdminResponse> admins=	adminRepository.findAll().stream().map(admin->
		adminMapper.mapToAdminResponse(admin))
		.toList();
	
	return ResponseEntity.status(HttpStatus.FOUND)
			.body(new ResponseStructure<List<AdminResponse>>()
			.setStatus(HttpStatus.FOUND.value())
			.setMessage("List of ADMINS Found")
			.setData(admins) );
	}

}
