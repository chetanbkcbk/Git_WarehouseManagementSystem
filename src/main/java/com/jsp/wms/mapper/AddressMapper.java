package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;import com.fasterxml.jackson.databind.jsontype.impl.AsDeductionTypeDeserializer;
import com.jsp.wms.entity.Address;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.responsedto.WareHouseResponse;

@Component
public class AddressMapper {

	public Address mapToAddress(AddressRequest addressRequest, Address address) {
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPincode(addressRequest.getPincode());
		address.setLatitude(addressRequest.getLatitude());
		address.setLongitude(addressRequest.getLongitude());
		
		return address;			
	}

	public AddressResponse mapToAddressResponse(Address address) { //to be shown in response
		return AddressResponse.builder() //builder is a static method henc use class name
				.addressId(address.getAddressId())
				.addressLine(address.getAddressLine())
				.city(address.getCity())
				.state(address.getState())
				.country(address.getCountry())
				.pincode(address.getPincode())
				.latitude(address.getLatitude())
				.longitude(address.getLongitude())
				.build();
				
		
	}

	
}
