package vn.cpa.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.cpa.api.dto.auth.api.ApiSignUpDTO;
import vn.cpa.api.dto.auth.api.ApiUserDTO;
import vn.cpa.api.entity.Account;
import vn.cpa.api.entity.SysUser;
import vn.cpa.api.exception.authentication.UserNotFoundHttpException;
import vn.cpa.api.exception.user.UserNotFoundException;
import vn.cpa.api.repository.auth.ApiUserRepository;
import vn.cpa.api.service.authentication.AuthenticationTokenService;
import vn.cpa.api.util.user.ChangePasswordRequest;
import vn.cpa.api.util.user.UserContextHolder;
import vn.cpa.api.util.user.builder.PageableBuilder;

import javax.transaction.Transactional;

@Service
@SuppressWarnings({"checkstyle:ParameterNumber"})
public class ApiUserService {

	private ApiUserRepository apiUserRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;
	private AuthenticationTokenService authenticationTokenService;
	private PageableBuilder pageableBuilder;

	@Value("${account.defaultImage}")
	private String defaultImage;

	@Autowired
	public ApiUserService(ApiUserRepository apiUserRepository,
                          PasswordEncoder passwordEncoder,
                          ModelMapper modelMapper,
                          AuthenticationTokenService authenticationTokenService,
                          PageableBuilder pageableBuilder) {
		this.apiUserRepository = apiUserRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.authenticationTokenService = authenticationTokenService;
		this.pageableBuilder = pageableBuilder;
	}

	public Account findByUsername(String username) throws UserNotFoundException {
		return apiUserRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("Account with username: " + username + " not found"));
	}

//	@Transactional
//	public void changePassword(ChangePasswordRequest changePasswordRequest) {
//		Account account = changePasswordRequest.getAccount();
//
//		String encodedPassword = encodePassword(changePasswordRequest.getPassword());
//		account.setPassword(encodedPassword);
//
//		apiUserRepository.save(account);
//	}

//	public ApiUserDTO getUserById(Long id) {
//		SysUser existingAccount = apiUserRepository.findById(id).orElseThrow(
//				() -> new UserNotFoundHttpException("Account with id: " + id + " not found", HttpStatus.NOT_FOUND)
//				);
//
//		return modelMapper.map(existingAccount, ApiUserDTO.class);
//	}

	@Transactional
	public ApiUserDTO updateUserById(Long userId, ApiUserDTO apiUserDTO) {
		return updateUser(userId, apiUserDTO);
	}

	@Transactional
	public boolean deleteUser(Long id) {
		try {
			apiUserRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundHttpException("Account with id: " + id + " not found", HttpStatus.NOT_FOUND);
		}
	}

	public ApiUserDTO getCurrentUser() {
		Account account = UserContextHolder.getUser();

		return modelMapper.map(account, ApiUserDTO.class);
	}

	public ApiUserDTO updateCurrentUser(ApiUserDTO apiUserDTO) {
		Account account = UserContextHolder.getUser();
		Long id = 1L;
		return updateUser(id, apiUserDTO);
	}

	@Transactional
	public ApiUserDTO createUser(ApiUserDTO apiUserDTO) {
		Account account = modelMapper.map(apiUserDTO, Account.class);

		// In current version password and role are default
		account.setPassword(encodePassword("testPass"));
//		account.setRoleId(DbConstant.ROLE_ID_USER);

		apiUserRepository.save(account);

		return modelMapper.map(account, ApiUserDTO.class);
	}

	private ApiUserDTO updateUser(Long id, ApiUserDTO apiUserDTO) {
		Account existingAccount = apiUserRepository.findById(id).
				orElseThrow(() -> new UserNotFoundHttpException(
						"Account with id: " + id + " not found", HttpStatus.NOT_FOUND)
						);
		Account updatedAccount = modelMapper.map(apiUserDTO, Account.class);
//		updatedAccount.setAccountId(id);
		updatedAccount.setPassword(existingAccount.getPassword());
		// Current version doesn't update roles
//		updatedAccount.setRoleId(existingAccount.getRoleId());
		apiUserRepository.save(updatedAccount);

		return modelMapper.map(updatedAccount, ApiUserDTO.class);
	}

	private SysUser signUpUser(ApiSignUpDTO apiSignUpDTO) {
		SysUser account = new SysUser();
//		account.setPhone(apiSignUpDTO.getPhoneNumber());
		// account.setLogin(apiSignUpDTO.getFullName());
		String encodedPassword = encodePassword(apiSignUpDTO.getPassword());
		account.setPassword(encodedPassword);
//		account.setRoleId(DbConstant.ROLE_ID_USER);
		//Set default settings and image

		return account;
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

//	private List<ApiUserDTO> mapOrdersToOrderDTO(List<Account> orders) {
//		return orders.stream().map(order -> {
//			ApiUserDTO dto = modelMapper.map(order, ApiUserDTO.class);
//			return dto;
//		}).collect(Collectors.toList());
//	}

//	private GridData<ApiUserDTO> parsePageToGridData(Page<Account> orderPages) {
//		GridData<ApiUserDTO> gridData = new GridData<>();
//		List<Account> orderList = orderPages.getContent();
//		long totalCount = orderPages.getTotalElements();
//		gridData.setItems(mapOrdersToOrderDTO(orderList));
//		gridData.setTotalCount(totalCount);
//		return gridData;
//	}

//	public GridData<ApiUserDTO> getDataForGrid(UsersGridFilter filter) {
//		UserSpecificationBuilder specificationBuilder = new UserSpecificationBuilder();
//
//		Pageable paginationAndSort = pageableBuilder.build(filter);
//		Optional<Specification<Account>> optionalSpec = specificationBuilder.build(filter);
//		Page<Account> orderPages = optionalSpec
//				.map(userSpecification -> apiUserRepository.findAll(userSpecification, paginationAndSort))
//				.orElseGet(() -> apiUserRepository.findAll(paginationAndSort));
//		return parsePageToGridData(orderPages);
//	}
}
