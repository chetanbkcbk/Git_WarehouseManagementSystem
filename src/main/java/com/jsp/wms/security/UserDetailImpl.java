package com.jsp.wms.security;

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
		return admin.getAdminType().getPrivileges()
				.stream()
				.map(privilege->new SimpleGrantedAuthority(privilege.name()))
				.toList();
				
				
				
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
