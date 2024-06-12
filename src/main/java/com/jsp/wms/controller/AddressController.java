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

import com.jsp.wms.entity.Address;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.service.AddressService;
import com.jsp.wms.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	

	@PostMapping("/warehouses/{wareHouseId}/addresses")
	@PreAuthorize("hasAuthority('CREATE_ADDRESS')")
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@RequestBody @Valid AddressRequest addressRequest,@PathVariable int wareHouseId){
		return addressService.addAddress(addressRequest,wareHouseId);
	}
	
	@PutMapping("/addresses/{addressId}")
	@PreAuthorize("hasAuthority('UPDATE_ADDRESS')")
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@RequestBody @Valid AddressRequest addressRequest,@PathVariable int addressId){
		return addressService.updateAddress(addressRequest,addressId);
	}
	
	@GetMapping("/addresses/{addressId}")
	@PreAuthorize("hasAuthority('READ')")
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(@PathVariable int addressId){
		return addressService.findAddress(addressId);
	}
	
}
