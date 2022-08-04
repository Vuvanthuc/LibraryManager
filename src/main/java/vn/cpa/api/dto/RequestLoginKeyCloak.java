package vn.cpa.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginKeyCloak {
	@Email
	private String username;
	private String password;
	@JsonProperty("client_id")
	private String clientId;
	@JsonProperty("grant_type")
	private String grantType;
	@JsonProperty("client_secret")
	private String clientSecret;
}
