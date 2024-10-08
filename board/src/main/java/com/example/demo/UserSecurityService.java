package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<SiteUser> _siteUser = this.userRepository.findByUsername(username);
		if(_siteUser.isEmpty()) {
			throw new UsernameNotFoundException("사용자가 없어요");
		}
		
		SiteUser siteUser = _siteUser.get();
		List<GrantedAuthority> auth = new ArrayList<>();
		if("admin".equals(username)) {
			auth.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {
			auth.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
		return new User(siteUser.getUsername(), siteUser.getPassword(), auth);
	}
	
	

}
