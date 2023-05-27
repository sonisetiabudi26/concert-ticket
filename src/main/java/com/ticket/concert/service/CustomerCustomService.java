package com.ticket.concert.service;

import com.ticket.concert.vo.CustomerVo;
import com.ticket.concert.domain.Customer;
import com.ticket.concert.repository.CustomerRepository;
import com.ticket.concert.security.UserPrincipal;
import com.ticket.concert.vo.CustomerVo;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerCustomService implements UserDetailsService {
	@Autowired
	private CustomerRepository custRepository;

	
    @Override
    @Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) {
		Customer user = custRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username or email: %s", usernameOrEmail)));
		return UserPrincipal.create(user);
	}

	// @Override
	@Transactional
	public UserDetails loadUserById(Long id) {
		Customer user = custRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id: %s", id)));

		return UserPrincipal.create(user);
	}


}


