package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.WareHouseService;
import com.jsp.wms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class WareHouseController {

	@Autowired
	private WareHouseService wareHouseService;
	
	
	@PostMapping("/warehouses")
public String	createWareHouse(@RequestBody WareHouseRequest wareHouseRequest)
{
		return  "Warehouse created";
	
}
}
