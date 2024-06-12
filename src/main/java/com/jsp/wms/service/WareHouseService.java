package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.WareHouseResponse;
import com.jsp.wms.util.ResponseStructure;


public interface WareHouseService {



	ResponseEntity<ResponseStructure<WareHouseResponse>> updateWarehouse(WareHouseRequest warehouseRequest,
			int warehouseId);

	ResponseEntity<ResponseStructure<WareHouseResponse>> createWarehouse(WareHouseRequest wareHouseRequest);

	ResponseEntity<ResponseStructure<WareHouseResponse>> findWareHouse(int wareHouseId);

	
}
