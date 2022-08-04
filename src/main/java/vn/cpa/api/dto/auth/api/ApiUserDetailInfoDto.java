package vn.cpa.api.dto.auth.api;

import vn.cpa.api.util.authentication.Tokens;

public class ApiUserDetailInfoDto {

	public String fullName;
	public String email;
	public String phone;
	public String dateOfBirth;
	public Integer sex;
	public String address;
	public String avatarPath;

	public Tokens tokens;

}
