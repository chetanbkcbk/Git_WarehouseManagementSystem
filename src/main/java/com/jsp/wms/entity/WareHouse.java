package com.jsp.wms.entity;

import com.jsp.wms.enums.AdminType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  //it indicates table
@Setter //will implicitly crete setter() will be visible in outline
@Getter	//will implicitly crete getter() will be visible in outline
@NoArgsConstructor //will implicitly crete noargsconstrutor() will be visible in outline
@AllArgsConstructor //will implicitly crete allargsconstrutor() will be visible in outline
@Builder
public class WareHouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //sql cretes autogeneratn strategy,no need of 'sequence table'
	
	private int wareHouseId;
	
	private String name; 
}
