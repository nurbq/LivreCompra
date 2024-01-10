package kz.kasky.cinemaroom.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final CustomUserDetailsService customUserDetailsService;
//
//    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeHttpRequests((request) -> request
//                        .requestMatchers("/register", "/register/*", "/login", "/movies").permitAll()
//                        .requestMatchers("/movies/form/", "/movieTheatres/form").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                ).formLogin((form) -> form.loginPage("/login")
//                        .failureUrl("/login?error=true")
//                        .defaultSuccessUrl("/movies")
//                        .permitAll())
//                .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());
//
//
//        return httpSecurity.build();
//    }
//
//    public void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}