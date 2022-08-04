package vn.cpa.api.service.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vn.cpa.api.config.Properties;
import vn.cpa.api.entity.Account;
import vn.cpa.api.entity.SysUser;
import vn.cpa.api.exception.authentication.AuthenticationException;
import vn.cpa.api.util.authentication.AuthenticationToken;
import vn.cpa.api.util.authentication.Tokens;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TokenService {


    private String accessTokenSecretKey;
    private String refreshTokenSecretKey;
    private UserDetailsService userDetailsService;
    private long accessTokenValidityInMilliseconds;
    private long refreshTokenValidityInMilliseconds;
    private AuthenticationTokenService authenticationTokenService;

    @Value("${keycloak.client_id}")
    private String clientId;
    @Value("${keycloak.grant_type}")
    private String grantType;
    @Value("${keycloak.client_secret}")
    private String secret;
    @Value("${keycloak.auth-server-url}")
    private String url;

    @Autowired
    public TokenService(@Qualifier("bundleUserDetailsService") UserDetailsService userDetailsService,
                        AuthenticationTokenService authenticationTokenService,
                        Properties properties) {
        this.userDetailsService = userDetailsService;
        this.authenticationTokenService = authenticationTokenService;
        accessTokenSecretKey = properties.getAccessTokenSecretKey();
        accessTokenValidityInMilliseconds = properties.getAccessTokenValidityInMilliseconds();
        refreshTokenSecretKey = properties.getRefreshTokenSecretKey();
        refreshTokenValidityInMilliseconds = properties.getRefreshTokenValidityInMilliseconds();
    }

    public Tokens createToken(Account account) {
        Tokens token = new Tokens();
        long expiresIn = expiration(accessTokenValidityInMilliseconds);
        token.setAccessToken(createAccessToken(account));
        token.setRefreshToken(createRefreshToken(account));
        token.setExpiresIn(new Date(expiresIn));
        return token;
    }

    public Authentication getAuthentication(AuthenticationToken token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(token.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    String getUsernameFromRefreshToken(String token) throws JwtException {
        return Jwts.parser().setSigningKey(refreshTokenSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public AuthenticationToken resolveToken(HttpServletRequest req) throws AuthenticationException {
        String bearer = "Bearer ";
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken == null) {
            throw new AuthenticationException("Authorization header should be present");
        }
        if (!bearerToken.startsWith(bearer)) {
            throw new AuthenticationException("Authorization header should begin with Bearer");
        }
        return authenticationTokenService.createToken(bearerToken.substring(bearer.length()));
    }

    private String createAccessToken(Account account) {
        long expiresIn = expiration(accessTokenValidityInMilliseconds);

        return createToken(account, expiresIn, accessTokenSecretKey);
    }

    private String createRefreshToken(Account account) {
        long expiresIn = expiration(refreshTokenValidityInMilliseconds);

        return createToken(account, expiresIn, refreshTokenSecretKey);
    }

    private String createToken(Account account, long expiresIn, String key) {
        Claims claims = Jwts.claims();
        claims.setSubject(account.getEmail());
        claims.put("fullName", account.getFullName());
        claims.put("email", account.getEmail());
        claims.put("username", account.getUsername());
        Date now = new Date();
        Date expirationDate = new Date(expiresIn);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    private long expiration(long validity) {
        Date now = new Date();
        return now.getTime() + validity;
    }
}
