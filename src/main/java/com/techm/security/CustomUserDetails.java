//
//package com.techm.security;
//
//import com.techm.entity.Users;
//import com.techm.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CustomUserDetails implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<Users> adminUser = userRepository.findByEmail(email);
//        if (adminUser.isPresent()) {
//            Users user = adminUser.get();
//            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
//            System.out.println("User: " + user.getEmail() + " Roles: " + authorities);
//            return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//            );
//        }
//        throw new UsernameNotFoundException("User not found: " + email);
//    }
//}
package com.techm.security;

import com.techm.entity.ServiceAdvisor;
import com.techm.entity.Users;
import com.techm.repository.ServiceAdvisorRepository;
import com.techm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceAdvisorRepository serviceAdvisorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> adminUser = userRepository.findByEmail(email);
        if (adminUser.isPresent()) {
            Users user = adminUser.get();
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
            System.out.println("User: " + user.getEmail() + " Roles: " + authorities);
            return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
            );
        }

        Optional<ServiceAdvisor> advisor = serviceAdvisorRepository.findByEmail(email);
        if (advisor.isPresent()) {
            ServiceAdvisor serviceAdvisor = advisor.get();
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ServiceAdvisor"));
            System.out.println("Advisor: " + serviceAdvisor.getEmail() + " Roles: " + authorities);
            return new org.springframework.security.core.userdetails.User(
                serviceAdvisor.getEmail(),
                serviceAdvisor.getPassword(),
                authorities
            );
        }

        throw new UsernameNotFoundException("User not found: " + email);
    }

    public Long getAdvisorIdByEmail(String email) {
        return serviceAdvisorRepository.findByEmail(email)
                .map(ServiceAdvisor::getId)
                .orElseThrow(() -> new UsernameNotFoundException("Advisor not found: " + email));
    }
}
