package com.ticket.concert.service;

import com.ticket.concert.domain.Customer;
import com.ticket.concert.repository.CustomerRepository;
import com.ticket.concert.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository custRepository;

	@Transactional(readOnly = true)
	public CustomerVo getUserByUsername(String usernameOrEmail) {
		Customer user = custRepository.findByUsername(usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username or email: %s", usernameOrEmail)));
		return mappingData(user);
	}

	private CustomerVo mappingData(Customer cust){
		CustomerVo data=new CustomerVo();
		data.setId(cust.getId());
		data.setCustomerName(cust.getCustomerName());
		data.setEmail(cust.getEmail());
		data.setUsername(cust.getUsername());
		return data;
	}

}


