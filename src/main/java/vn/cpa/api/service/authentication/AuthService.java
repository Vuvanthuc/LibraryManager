package vn.cpa.api.service.authentication;

import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.cpa.api.dto.LoginDTO;
import vn.cpa.api.dto.RefreshTokenDTO;
import vn.cpa.api.entity.Account;
import vn.cpa.api.entity.SysUser;
import vn.cpa.api.exception.AccountBlockException;
import vn.cpa.api.exception.authentication.InvalidTokenHttpException;
import vn.cpa.api.exception.authentication.UserNotFoundHttpException;
import vn.cpa.api.exception.user.UserNotFoundException;
import vn.cpa.api.repository.account.AccountRepository;
import vn.cpa.api.repository.auth.ApiUserRepository;
import vn.cpa.api.service.ApiUserService;
import vn.cpa.api.util.MessageUtil;
import vn.cpa.api.util.StringUtil;
import vn.cpa.api.util.authentication.Tokens;

import java.util.Optional;

@Log4j2
@Service
public class AuthService {

	private ApiUserService apiUserService;
	private AuthenticationManager authenticationManager;
	private TokenService tokenService;

	@Autowired
	private AccountRepository accountRepository;


	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public AuthService(ApiUserService apiUserService,
					   AuthenticationManager authenticationManager,
					   TokenService tokenService) {
		this.apiUserService = apiUserService;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

//	public Tokens register(ApiSignUpDTO apiSignUpDTO) throws UserAlreadyExistsHttpException {
//		try {
//			SysUser account = apiUserService.register(apiSignUpDTO);
//			return createToken(account);
//		} catch (UserAlreadyExistsException exception) {
//			throw new UserAlreadyExistsHttpException();
//		}
//	}

	public Tokens login(LoginDTO loginDTO) throws UserNotFoundHttpException, UserNotFoundException, AccountBlockException {
		try {
			Authentication authentication = createAuthentication(loginDTO);
			BundleUserDetailsService.BundleUserDetails userDetails =
					(BundleUserDetailsService.BundleUserDetails) authenticationManager
							.authenticate(authentication).getPrincipal();
			Account account = userDetails.getAccount();
			return createToken(account);
		} catch (AuthenticationException exception) {
			log.error(exception);
			throw new UserNotFoundHttpException(MessageUtil.INCORRECT_PASSWORD, HttpStatus.FORBIDDEN);
		}
	}

	public Tokens refreshToken(RefreshTokenDTO refreshTokenDTO) throws InvalidTokenHttpException {
		try {
			String username = tokenService.getUsernameFromRefreshToken(refreshTokenDTO.getTokens().getRefreshToken());
			Account account = apiUserService.findByUsername(username);
			return createToken(account);
		} catch (JwtException | UserNotFoundException e) {
			throw new InvalidTokenHttpException();
		}
	}

	private Authentication createAuthentication(LoginDTO loginDTO) throws AccountBlockException, UserNotFoundException {
		Optional<Account> account = accountRepository.findByUsername(loginDTO.getUsername().toLowerCase());
		if(account.get().getStatus() == 0){
			throw new AccountBlockException("Tài khoản đã bị khóa");
		}
		if (!account.get().getPassword().equals(StringUtil.encryptPassword(loginDTO.getPassword(), account.get().getSalt()))) {
			throw new UserNotFoundException("Tài khoản hoặc mật khẩu không đúng");
		}

		return new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword() + account.get().getSalt());
	}

	private Tokens createToken(Account account) {
		return tokenService.createToken(account);
	}

}
