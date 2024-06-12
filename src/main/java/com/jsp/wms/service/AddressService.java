package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.entity.Address;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.util.ResponseStructure;

import jakarta.validation.Valid;



public interface AddressService {

	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest,int wareHouseId);

	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(  @Valid AddressRequest addressRequest, int addressId);

	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId);

}
