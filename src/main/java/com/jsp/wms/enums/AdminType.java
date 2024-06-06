package com.jsp.wms.enums;

import java.util.List;

public enum AdminType {
SUPER_ADMIN(List.of(Privileges.CREATE_ADMIN,Privileges.CREATE_ADDRESS,Privileges.CREATE_WAREHOUSE,Privileges.CREATE_STORAGE
		,Privileges.READ,Privileges.UPDATE_ADDRESS,Privileges.UPDATE_ADMIN,Privileges.UPDATE_WAREHOUSE,Privileges.UPDATE_STORAGE
		,Privileges.DELETE_ADDRESS,Privileges.DELETE_ADMIN,Privileges.DELETE_STORAGE,Privileges.DELETE_WAREHOUSE)),
	ADMIN(List.of(Privileges.CREATE_STORAGE,Privileges.READ,Privileges.UPDATE_ADMIN,Privileges.UPDATE_STORAGE,Privileges.DELETE_STORAGE));
	
	private List<Privileges> privileges;

	 AdminType(List<Privileges> privileges) {   //default specifier
		this.privileges = privileges;
	}
	
	 public List<Privileges> getPrivileges(){
		 return this.privileges;
	 }
	
	
	
	
}

