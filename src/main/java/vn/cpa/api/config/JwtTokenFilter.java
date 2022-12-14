package vn.cpa.api.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import vn.cpa.api.exception.authentication.AuthenticationException;
import vn.cpa.api.service.authentication.TokenService;
import vn.cpa.api.util.authentication.AuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private TokenService tokenService;

    JwtTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            AuthenticationToken token = tokenService.resolveToken((HttpServletRequest) req);
            if (token.isExpired()) {
                filterChain.doFilter(req, res);
                return;
            }

            Authentication auth = tokenService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(req, res);
    }
}
