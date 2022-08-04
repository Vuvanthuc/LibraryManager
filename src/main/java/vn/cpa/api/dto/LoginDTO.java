package vn.cpa.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginDTO {

	@NotEmpty
	@NotNull
	@Email
	private String username;


	@NotNull
	@NotEmpty
	@JsonProperty("password")
	private String password;

}
