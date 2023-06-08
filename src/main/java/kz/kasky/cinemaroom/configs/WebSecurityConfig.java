package kz.kasky.cinemaroom.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//    private final JpaUserDetailsService jpaUserDetailsService;
//
//
//    public WebSecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
//        this.jpaUserDetailsService = jpaUserDetailsService;
//    }

    @Bean
    public UserDetailsService users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/movies/form", "/schedules/create", "/movieTheater/form").hasRole("ADMIN")
                        .requestMatchers("/", "/home", "/movies", "/movieTheater", "/register","/register/*", "/login", "/login/*").permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/movies")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()).build();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home", "/register", "/register/*", "/movies", "/movieTheatres", "/login", "/login*").permitAll()
//                        .requestMatchers("/movies/form", "/schedules/*", "/moviesTheater/form").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                                .loginPage("/login")
////                        .defaultSuccessUrl("/movies")
////                        .loginProcessingUrl("/login")
//                                .failureUrl("/login?error=true")
//                                .permitAll()
//                )
//                .logout((logout) -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());
//
//        return http.build();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return new ProviderManager(daoAuthenticationProvider);
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    public void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(jpaUserDetailsService).passwordEncoder(passwordEncoder());
//    }


}
