package com.jsp.wms.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Address;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.exception.AddressNotFoundByIdException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.AddressMapper;
import com.jsp.wms.repository.AddressRepository;
import com.jsp.wms.repository.AdminRepository;
import com.jsp.wms.repository.WareHouseRepository;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.responsedto.WareHouseResponse;
import com.jsp.wms.service.AddressService;
import com.jsp.wms.util.ResponseStructure;

@Service
public class AddressServiceImpl implements AddressService {


	
	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest,int wareHouseId) {
		return wareHouseRepository.findById(wareHouseId).map(warehouse->{
			 Address address = addressMapper.mapToAddress(addressRequest, new Address());
			 address.setWareHouse(warehouse);
			 address = addressRepository.save(address);
		 
			 return	ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseStructure<AddressResponse>()
								.setStatus(HttpStatus.CREATED.value())
								.setMessage("AddressCreated")
								.setData(addressMapper.mapToAddressResponse(address)));	
			}).orElseThrow(()->new WarehouseNotFoundByIdException("WareHouse of this id not found")	 );
		
		 }

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest,int addressId) {

	return	addressRepository.findById(addressId ).map(exaddress->{
			addressMapper.mapToAddress(addressRequest, exaddress);
			Address updatedAddress = addressRepository.save(exaddress);
			return ResponseEntity.status(HttpStatus.OK)	
					.body(new ResponseStructure<AddressResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Address Updated")
							.setData(addressMapper.mapToAddressResponse(updatedAddress)));
			}).orElseThrow(()->new AddressNotFoundByIdException("Address Not Found By Id"));
						
					
			}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId) {

	return	addressRepository.findById(addressId).map(address->
		{
			AddressResponse addressResponse = addressMapper.mapToAddressResponse(address);
		
			return ResponseEntity
					.status(HttpStatus.FOUND)
		.body(new ResponseStructure<AddressResponse>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("Address Found")
				.setData(addressResponse) );
		}).orElseThrow(()->new AddressNotFoundByIdException("Address with such Id Not found")) ;
		
		
	}
	}


