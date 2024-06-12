package com.jsp.wms.security;
//2nd
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.wms.repository.AdminRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return adminRepository.findByEmail(username) //find <Optional>Admin based on email
				.map(UserDetailImpl::new)  //this map() is present in Optional . Dont confuse this with map() of Streams 
				//map() takes admin as parameter and converts into UserDetails
				
				.orElseThrow(()->new UsernameNotFoundException("User Not Found"));
								//handle this predefined Exceotion Exceptionhandler, even though we have not created this class
				
				/*or 	.map(admin->new UserDetailImpl(admin))
						.orElseThrow(()->new UsernameNotFoundException("User Not Found"));  */
	}

}
