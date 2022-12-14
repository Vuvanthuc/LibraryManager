package vn.cpa.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import vn.cpa.api.service.authentication.TokenService;

import java.util.Arrays;

@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            // -- h2 database console
            "/h2-console/**"
    };

    @Autowired
    public JwtSecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public JwtSecurityConfig(TokenService tokenService, boolean disableDefaults) {
        super(disableDefaults);
        this.tokenService = tokenService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();

        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/restore-pass").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/sign-up").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/request-pass").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/account/forget_password").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/account/confirmation_otp").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/sign-out").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/refresh-token").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/users/{id}/photo").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/verify/otp/send").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/account/check_otp").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/verify/otp").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated();
        http.apply(new JwtConfigurer(tokenService));
        http.cors().and();
        // Allows frame same origin. Uses for h2 console. Should be removed after integrating with real database.
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "PUT", "DELETE"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
