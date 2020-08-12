package com.gameduos.springboot.web.config;

import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.oauth2.CustomOAuth2Provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.gameduos.springboot.web.domain.user.Role.*;
import static com.gameduos.springboot.web.domain.user.SocialType.*;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");                            //요청을 보내는 페이지의 출처 (*, 도메인)
        configuration.addAllowedMethod("*");                            //요청을 허용하는 메소드 (Default : GET, POST, HEAD)
        configuration.addAllowedHeader("*");                            // 요청을 허용하는 헤더

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        http
                .authorizeRequests() //구체적인걸 위로
                .antMatchers("/facebook").hasAuthority(FACEBOOK.getRoleType())
                .antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
                .antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
                .antMatchers("/myPage/certification").hasAnyAuthority(GUEST.getRoleType(), ADMIN.getRoleType(), MASTER.getRoleType())
                .antMatchers(HttpMethod.GET ,"/api/user/nicknames/**").hasAnyAuthority(GUEST.getRoleType(), USER.getRoleType(),
                                                                        ADMIN.getRoleType(), MASTER.getRoleType(), INTERVIEWER.getRoleType())
                .antMatchers(HttpMethod.PUT ,"/api/referralCode/**").hasAnyAuthority(GUEST.getRoleType(), USER.getRoleType(),
                                                                        ADMIN.getRoleType(), MASTER.getRoleType(), INTERVIEWER.getRoleType())
                .antMatchers("/admin/api/**").hasAnyAuthority(MASTER.getRoleType(), ADMIN.getRoleType())
                .antMatchers("/admin/users/**").hasAnyAuthority(MASTER.getRoleType(), ADMIN.getRoleType(), INTERVIEWER.getRoleType())
                .antMatchers("/master/**", "/swagger-ui.html#", "/console/**").hasAuthority(MASTER.getRoleType())
                .antMatchers("/admin/**").hasAnyAuthority(MASTER.getRoleType(), ADMIN.getRoleType(),INTERVIEWER.getRoleType())
                .antMatchers("/home", "/myPage/**").hasAnyAuthority(GUEST.getRoleType(), USER.getRoleType(),
                                                                                ADMIN.getRoleType(), MASTER.getRoleType(), INTERVIEWER.getRoleType())
                .antMatchers("/", "/oauth2/**", "/login/**",  "/css/**",
                        "/images/**", "/js/**", "/personalTest/**", "/api/user/delete",
                        "/loginSuccess", "/loginFailure", "/logout", "/error/**", "/customLogout", "/profile", "/favicon.ico", "/fonts/**").permitAll()
                .anyRequest().hasAnyAuthority(USER.getRoleType(), ADMIN.getRoleType(), MASTER.getRoleType(), INTERVIEWER.getRoleType())
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/loginSuccess")
                    .failureUrl("/loginFailure")
                .and()
                    .headers().frameOptions().disable()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()
                    .formLogin()
                    .successForwardUrl("/home")
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                .and()
                    .cors().configurationSource(source)
                .and()
                    .addFilterBefore(filter, CsrfFilter.class)
                    .csrf()
                        .ignoringAntMatchers("/console/**")
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login")
                .maxSessionsPreventsLogin(false)
        ;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties oAuth2ClientProperties, @Value("${custom.oauth2.kakao.client-id}") String kakaoClientId) {
        List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
                .map(client -> getRegistration(oAuth2ClientProperties, client))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
                .clientId(kakaoClientId)
                .clientSecret("test") //필요없는 값인데 null이면 실행이 안되도록 설정되어 있음
                .jwkSetUri("test") //필요없는 값인데 null이면 실행이 안되도록 설정되어 있음
                .build());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
        if ("google".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("email", "profile")
                    .build();
        }
        if ("facebook".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("facebook");
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link")
                    .scope("email")
                    .build();
        }
        return null;
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

}