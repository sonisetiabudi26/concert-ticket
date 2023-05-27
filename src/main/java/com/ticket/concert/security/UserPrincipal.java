package com.ticket.concert.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticket.concert.domain.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String customerName;

	private String username;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;


	public UserPrincipal(Long id, String customerName,  String username, String email, String password) {
		this.id = id;
		this.customerName = customerName;
		this.username = username;
		this.email = email;
		this.password = password;

	}

	public static UserPrincipal create(Customer user) {
		
		return new UserPrincipal(user.getId(), user.getCustomerName(), user.getUsername(),
				user.getEmail(), user.getPassword());
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	
	public String getCustomerName(){
		return customerName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities == null ? null : new ArrayList<>(authorities);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}