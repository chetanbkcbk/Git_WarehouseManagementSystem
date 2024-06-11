package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.responsedto.WareHouseResponse;

@Component
public class WareHouseMapper {

	
		
		public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse) {
			return WareHouseResponse.builder()
					.wareHouseId(wareHouse.getWareHouseId())
					.name(wareHouse.getName())
					.totalCapacity(0)
					.build();
		}

		public WareHouse mapToWareHouse(WareHouseRequest wareHouseRequest, WareHouse wareHouse) {
			wareHouse.setName(wareHouseRequest.getName());
			return wareHouse;			
		}

			
}
