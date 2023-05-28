package com.ticket.concert.repository;

import java.util.Optional;
import javax.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ticket.concert.domain.Customer;
import com.ticket.concert.exception.ResourceNotFoundException;
import com.ticket.concert.security.UserPrincipal;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByUsername(@NotBlank String username);

	Optional<Customer> findByEmail(@NotBlank String email);

	Boolean existsByUsername(@NotBlank String username);

	Boolean existsByEmail(@NotBlank String email);

	Optional<Customer> findByUsernameOrEmail(String username, String email);

	default Customer getUser(UserPrincipal currentUser) throws Exception {
		return getUserByName(currentUser.getUsername());
	}

	default Customer getUserByName(String username) throws Exception {
		return findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
	}

	default Customer getUserByEmail(String email) throws Exception {
		return findByEmail(email).orElseThrow(() -> new Exception("User"));
	}
}