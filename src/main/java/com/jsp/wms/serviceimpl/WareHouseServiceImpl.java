package com.jsp.wms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.mapper.WareHouseMapper;
import com.jsp.wms.repository.AdminRepository;
import com.jsp.wms.repository.WareHouseRepository;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.responsedto.WareHouseResponse;
import com.jsp.wms.service.WareHouseService;
import com.jsp.wms.util.ResponseStructure;

@Service
public class WareHouseServiceImpl implements WareHouseService{

	
	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Autowired
	private WareHouseMapper wareHouseMapper;
	
	

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWarehouse(WareHouse wareHouseRequest) {

		
		WareHouse warehouse= wareHouseRepository.save(wareHouseMapper.mapToWareHouse(wareHouseRequest,new WareHouse()));
        wareHouseRepository.save(warehouse);
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ResponseStructure<WareHouseResponse>()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Warehouse created")
                .setData(wareHouseMapper.mapToWareHouseResponse(warehouse)));	
		
		}



}
