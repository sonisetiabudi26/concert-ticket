package com.ticket.concert.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticket.concert.domain.Customer;


@Data
public class AuthenticationVo {
	
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	private String accessToken;
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	private String tokenType = "Bearer";
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private CustomerVo user;

    @NotBlank
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	public String username;

	@NotBlank
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private String password;

    public AuthenticationVo(CustomerVo userInfo,String accessToken) {
		this.accessToken = accessToken;
		this.user=userInfo;
	}
	

	// public final void setData(List user) {

	// 	if (user == null) {
	// 		this.user = null;
	// 	} else {
	// 		this.user = Collections.unmodifiableList(user);
	// 	}
	// }
	
}
