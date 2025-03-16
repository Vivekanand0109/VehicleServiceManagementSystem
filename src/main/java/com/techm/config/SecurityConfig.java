//////
//////package com.techm.config;
//////
//////import com.techm.security.CustomUserDetails;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.context.annotation.Configuration;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//////import org.springframework.security.web.SecurityFilterChain;
//////
//////@Configuration
//////@EnableWebSecurity
//////public class SecurityConfig {
//////
//////    @Autowired
//////    private CustomUserDetails customUserDetails;
//////
//////    @Bean
//////    public BCryptPasswordEncoder passwordEncoder() {
//////        return new BCryptPasswordEncoder();
//////    }
//////
//////    @Bean
//////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////        http
//////            .authorizeHttpRequests(auth -> auth
//////                .requestMatchers("/admin-login", "/css/**").permitAll()
//////                .requestMatchers("/admin/**").hasRole("Admin")
//////                .anyRequest().authenticated()
//////            )
//////            .formLogin(form -> form
//////                .loginPage("/admin-login")
//////                .loginProcessingUrl("/admin-login")
//////                .successHandler((request, response, authentication) -> {
//////                    System.out.println("Admin login successful: " + authentication.getName());
//////                    response.sendRedirect("/admin/dashboard");
//////                })
//////                .failureUrl("/admin-login?error=true")
//////                .permitAll()
//////            )
//////            .logout(logout -> logout
//////                .logoutUrl("/logout")
//////                .logoutSuccessUrl("/admin-login?logout")
//////                .permitAll()
//////            );
//////        return http.build();
//////    }
//////}
//////
//////
////package com.techm.config;
////
////import com.techm.security.CustomUserDetails;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Autowired
////    private CustomUserDetails customUserDetails;
////
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////            .authorizeHttpRequests(auth -> auth
////                .requestMatchers("/admin-login", "/css/**").permitAll()
////                .requestMatchers("/admin/**").hasRole("Admin")
////                .anyRequest().authenticated()
////            )
////            .formLogin(form -> form
////                .loginPage("/admin-login")
////                .loginProcessingUrl("/admin-login")
////                .successHandler((request, response, authentication) -> {
////                    System.out.println("Admin login successful: " + authentication.getName());
////                    response.sendRedirect("/admin/dashboard");
////                })
////                .failureUrl("/admin-login?error=true")
////                .permitAll()
////            )
////            .logout(logout -> logout
////                .logoutUrl("/logout")
////                .logoutSuccessUrl("/admin-login?logout")
////                .invalidateHttpSession(true) // Invalidate session
////                .deleteCookies("JSESSIONID") // Clear session cookie
////                .permitAll()
////            );
////        return http.build();
////    }
////}
////
//
//
//
//package com.techm.config;
//
//import com.techm.security.CustomUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
//
//    @Autowired
//    private CustomUserDetails customUserDetails;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/login", "/admin/register", "/css/**").permitAll()
//                .requestMatchers("/admin/**").hasRole("Admin")
//                .requestMatchers("/advisor/**").hasRole("ServiceAdvisor")
//                .anyRequest().authenticated()
//            )
//            .formLogin(form -> form
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .successHandler((request, response, authentication) -> {
//                    String email = authentication.getName();
//                    logger.info("Login successful: " + email);
//                    boolean isAdmin = authentication.getAuthorities().stream()
//                            .anyMatch(a -> a.getAuthority().equals("ROLE_Admin"));
//                    if (isAdmin) {
//                        response.sendRedirect("/admin/dashboard");
//                    } else {
//                        Long advisorId = customUserDetails.getAdvisorIdByEmail(email);
//                        response.sendRedirect("/advisor/dashboard/" + advisorId);
//                    }
//                })
//                .failureUrl("/login?error=true")
//                .permitAll()
//            )
//            .logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?login")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessHandler((request, response, authentication) -> {
//                    logger.info("Logout successful for user: " + (authentication != null ? authentication.getName() : "unknown"));
//                    response.sendRedirect("/login?login");
//                })
//                .permitAll()
//            )
//            .exceptionHandling(ex -> ex
//                .accessDeniedHandler((request, response, accessDeniedException) -> {
//                    logger.warn("Access denied for request: " + request.getRequestURI());
//                    response.sendRedirect("/login?accessDenied");
//                })
//            );
//        return http.build();
//    }
//}






package com.techm.config;

import com.techm.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private CustomUserDetails customUserDetails;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/admin/register", "/css/**").permitAll()
                .requestMatchers("/admin/**").hasRole("Admin")
                .requestMatchers("/advisor/**").hasRole("ServiceAdvisor")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    String email = authentication.getName();
                    logger.info("Login successful: " + email);
                    boolean isAdmin = authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_Admin"));
                    if (isAdmin) {
                        response.sendRedirect("/admin/dashboard");
                    } else {
                        Long advisorId = customUserDetails.getAdvisorIdByEmail(email);
                        response.sendRedirect("/advisor/dashboard/" + advisorId);
                    }
                })
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler((request, response, authentication) -> {
                    logger.info("Logout successful for user: " + (authentication != null ? authentication.getName() : "unknown"));
                    response.sendRedirect("/login?login");
                })
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    logger.warn("Access denied for request: " + request.getRequestURI());
                    response.sendRedirect("/login?accessDenied");
                })
            );
        return http.build();
    }
}

