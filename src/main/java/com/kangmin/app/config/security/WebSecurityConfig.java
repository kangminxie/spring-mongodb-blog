package com.kangmin.app.config.security;

import com.kangmin.app.config.jwt.JwtAuthenticationEntryPoint;
import com.kangmin.app.config.jwt.JwtProvider;
import com.kangmin.app.config.jwt.JwtTokenVerificationFilter;
import com.kangmin.app.config.oauth2.OAuth2UserService;
import com.kangmin.app.config.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.kangmin.app.config.oauth2.OAuth2AuthenticationFailureHandler;
import com.kangmin.app.config.oauth2.OAuth2AuthenticationSuccessHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.swing.*;
import java.util.Arrays;

import static com.kangmin.app.model.security.WebUserPermission.ACCOUNT_READ;
import static com.kangmin.app.model.security.WebUserRole.ADMIN;
import static com.kangmin.app.model.security.WebUserRole.SUPER_ADMIN;

@Import({
    PasswordEncoderConfig.class,
})
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService webUserDetailsService;
    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final OAuth2UserService oAuth2UserService;

    public WebSecurityConfig(
        final PasswordEncoder passwordEncoder,
        final UserDetailsService webUserDetailsService,
        final JwtProvider jwtProvider,
        final JwtAuthenticationEntryPoint unauthorizedHandler,
        final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository,
        final OAuth2UserService oAuth2UserService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.webUserDetailsService = webUserDetailsService;
        this.jwtProvider = jwtProvider;
        this.unauthorizedHandler = unauthorizedHandler;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
        this.oAuth2UserService = oAuth2UserService;
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable()
            .and()
            .authorizeRequests()
            .antMatchers("/",
                "/favicon.ico",
                "/static/**",
                "/resources/*",
                "/error", "/error/",
                "/index",
                "/about",
                "/contact",
                "/auth/login", "/api/auth/login",
                "/auth/register", "/api/auth/register",
                "/oauth/*",
                "/h2-console",
                "/h2-console/*",
                "/oauth2/authorize/*",
                "/devTest")
            .permitAll()

            // debug
            .antMatchers("/api/courses/**")
            .authenticated()

            .antMatchers("/api/accounts/**")
            .hasAnyAuthority(ACCOUNT_READ.getName())

            .antMatchers("/admin/**")
            .hasAnyRole(ADMIN.name(), SUPER_ADMIN.name())

            .anyRequest()
            .authenticated()
            // oauth2
            .and()
            .oauth2Login()
            .authorizationEndpoint()
            .baseUri("/oauth2/authorize")
            .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
            .and()
            .redirectionEndpoint()
            .baseUri("/oauth2/callback/*")
            .and()
            .userInfoEndpoint()
            .userService(oAuth2UserService)
            .and()
            .successHandler(oAuth2AuthenticationSuccessHandlerBean())
            .failureHandler(oAuth2AuthenticationFailureHandlerBean())
            // jwt
            .and()
            .addFilterBefore(jwtTokenVerificationFilterBean(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // == DaoAuthentication ==

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(webUserDetailsService);
        return provider;
    }

    // == JWT ==

    @Bean
    public JwtTokenVerificationFilter jwtTokenVerificationFilterBean() {
        return new JwtTokenVerificationFilter(jwtProvider);
    }

    // == OAuth2 ==

    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandlerBean() {
        return new OAuth2AuthenticationSuccessHandler(jwtProvider, httpCookieOAuth2AuthorizationRequestRepository);
    }

    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandlerBean() {
        return new OAuth2AuthenticationFailureHandler(httpCookieOAuth2AuthorizationRequestRepository);
    }

    /*
        "If you are using Spring MVC’s CORS support, you can omit specifying the CorsConfigurationSource
        and Spring Security will leverage the CORS configuration provided to javax.swing.Spring MVC."
        https://docs.spring.io/spring-security/site/docs/current/reference/html5/
        https://spring.io/blog/2015/06/08/cors-support-in-spring-framework#filter-based-cors-support
        Therefore, I have implemented this part in WebMvcConfig
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",
            "http://localhost:3001",
            "http://localhost:4200",
            "http://localhost:5000"
        ));
        configuration.setAllowedMethods(Arrays.asList(
            "HEAD",
            "OPTIONS",
            "GET",
            "POST",
            "PUT",
            "PATCH",
            "DELETE"
        ));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
