package vn.cpa.api.dto.auth.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ApiUserDetailUpdateDto {

	@NotEmpty
	public String fullName;

	@ApiModelProperty(example = "dd/MM/yyyy")
	public String dateOfBirth;

	@NotEmpty
	public String phone;

	@NotNull
	public Integer sex;

	public String address;

	@NotEmpty
	public String email;

	@JsonIgnore
	@ApiModelProperty(hidden=true)
	public String avatarPath;

	@JsonIgnore
	@ApiModelProperty(hidden=true)
	public Long ncAccountId;

	@JsonIgnore
	@ApiModelProperty(hidden=true)
	public String staticContext;

}
