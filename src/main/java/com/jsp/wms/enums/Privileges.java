package com.jsp.wms.enums;

//enums cannot have Setter methods ,but can have constructors
//Access Specifier for the constructors shud be default,
//once we create Privileges,We shud stop invoking Privileges methods everywhere
public enum Privileges {

	CREATE_ADMIN,
	CREATE_WAREHOUSE,
	CREATE_ADDRESS,
	CREATE_STORAGE,
	
	READ,
	
	UPDATE_ADMIN,
	UPDATE_WAREHOUSE,
	UPDATE_ADDRESS,
	UPDATE_STORAGE,
	
	DELETE_ADMIN,
	DELETE_WAREHOUSE,
	DELETE_ADDRESS,
	DELETE_STORAGE;
	
}
