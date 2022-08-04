package vn.cpa.api.controller;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.cpa.api.dto.LoginDTO;
import vn.cpa.api.exception.AccountBlockException;
import vn.cpa.api.exception.user.UserNotFoundException;
import vn.cpa.api.service.authentication.AuthService;
import vn.cpa.api.util.authentication.Tokens;

@Api(tags = "API auth")
@RestController
@RequestMapping("/api/v1/auth")
@Validated
@Log4j2
public class ApiAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws UserNotFoundException, AccountBlockException {
        if (StringUtils.isBlank(loginDTO.getUsername())) {
            return new ResponseEntity<>("Bạn vui lòng nhập username", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (StringUtils.isBlank(loginDTO.getPassword())) {
            return new ResponseEntity<>("Bạn vui lòng nhập mật khẩu", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Tokens tokens = authService.login(loginDTO);
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }
}
