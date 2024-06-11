package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.responsedto.WareHouseResponse;
import com.jsp.wms.service.WareHouseService;
import com.jsp.wms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class WareHouseController {

	@Autowired
	private WareHouseService wareHouseService;
	
	
	@PostMapping("/warehouses")
	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")  //to allow only those who have the reqrd authority will be allowed to access the resources
public ResponseEntity<ResponseStructure<WareHouseResponse>>	createWareHouse(@RequestBody WareHouseRequest wareHouseRequest)
{
		return  wareHouseService.createWarehouse(wareHouseRequest);
}
	
	@PreAuthorize("hasAuthority('UPDATE_WAREHOUSE')")
	@PutMapping("/warehouses/{warehouseId}")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWarehouse(@RequestBody WareHouseRequest warehouseRequest, @PathVariable int warehouseId){
		return wareHouseService.updateWarehouse(warehouseRequest , warehouseId);
	}
}
