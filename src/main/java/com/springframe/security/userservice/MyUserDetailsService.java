package com.springframe.security.userservice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pojo.Resources;
import com.service.ResourcesService;
import com.service.UserService;

@Component
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	@Qualifier(value="userService")
	private UserService userService;

	@Autowired
	@Qualifier(value="resourcesService")
	private ResourcesService resourcesService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 com.pojo.User user = userService.findUserByName(username);
			if(user ==null)
	            throw new UsernameNotFoundException(username+" not exist!");  
			Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
			Resources resources = new Resources();
			resources.setUsername(username);
			List<Resources> list = resourcesService.loadMenu(resources);
			for (Resources r : list) {
				authSet.add(new SimpleGrantedAuthority("ROLE_" +r.getResKey()));
			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), 
					user.getPassword(), 
					user.getEnable()==1?true:false, 
					true, 
					true,
					true,
					authSet);
}
}