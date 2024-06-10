package com.jsp.wms.security;
//1st
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.wms.entity.Admin;

import lombok.AllArgsConstructor;

@AllArgsConstructor //shud have only All argsConstructor to avoid null pointer exception 
public class UserDetailImpl implements UserDetails{  //this shud be used while 

	
	private Admin admin;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return admin.getAdminType().getPrivileges()//will return list of privileges
				.stream() //and use stream(),which converts it into streams of privilege and then callinh map() inside stream() to iterate over each privilege
				.map(privilege->new SimpleGrantedAuthority(privilege.name()))//&since i want to convert each privilege instance into Simple granted authority,i have to use map() coz it can take any parameter and can return anything as return type and that is possible coz it has Function interface as parameter
				   // now map()  returns 'Stream of Collection of Simple Granted Authority'	

				.toList();//returns the list inside that stream								  //privilege.name() returns String format of htis enum
				
		
				
			//shud return child object of Granted Authority
	}

	@Override
	public String getPassword() {
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		
		return admin.getEmail();//return something thats unique
	}



}
