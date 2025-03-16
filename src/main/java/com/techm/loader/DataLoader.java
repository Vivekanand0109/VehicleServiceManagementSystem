//
//package com.techm.loader;
//
//import com.techm.entity.ServiceAdvisor;
//import com.techm.entity.Users;
//import com.techm.repository.ServiceAdvisorRepository;
//import com.techm.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ServiceAdvisorRepository serviceAdvisorRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) {
//        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
//            Users admin = new Users();
//            admin.setUserName("Admin User");
//            admin.setEmail("admin@example.com");
//            admin.setPhone("1234567890");
//            admin.setPassword(passwordEncoder.encode("admin123"));
//            admin.setRole(Users.Role.Admin);
//            userRepository.save(admin);
//        }
//
//        if (serviceAdvisorRepository.findByEmail("mike.ross@example.com").isEmpty()) {
//            ServiceAdvisor advisor1 = new ServiceAdvisor();
//            advisor1.setName("Mike Ross");
//            advisor1.setEmail("mike.ross@example.com");
//            advisor1.setPassword(passwordEncoder.encode("advisor123")); // Still stored for reference
//            serviceAdvisorRepository.save(advisor1);
//        }
//        if (serviceAdvisorRepository.findByEmail("harvey.specter@example.com").isEmpty()) {
//            ServiceAdvisor advisor2 = new ServiceAdvisor();
//            advisor2.setName("Harvey Specter");
//            advisor2.setEmail("harvey.specter@example.com");
//            advisor2.setPassword(passwordEncoder.encode("advisor123")); // Still stored for reference
//            serviceAdvisorRepository.save(advisor2);
//        }
//    }
//}
//
package com.techm.loader;

import com.techm.entity.ServiceAdvisor;
import com.techm.entity.Users;
import com.techm.repository.ServiceAdvisorRepository;
import com.techm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceAdvisorRepository serviceAdvisorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            Users admin = new Users();
            admin.setUserName("Admin User");
            admin.setEmail("admin@example.com");
            admin.setPhone("1234567890");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Users.Role.Admin);
            userRepository.save(admin);
        }

        if (serviceAdvisorRepository.findByEmail("mike.ross@example.com").isEmpty()) {
            ServiceAdvisor advisor1 = new ServiceAdvisor();
            advisor1.setName("Mike Ross");
            advisor1.setEmail("mike.ross@example.com");
            advisor1.setPassword(passwordEncoder.encode("advisor123"));
            serviceAdvisorRepository.save(advisor1);
        }
        if (serviceAdvisorRepository.findByEmail("harvey.specter@example.com").isEmpty()) {
            ServiceAdvisor advisor2 = new ServiceAdvisor();
            advisor2.setName("Harvey Specter");
            advisor2.setEmail("harvey.specter@example.com");
            advisor2.setPassword(passwordEncoder.encode("advisor123"));
            serviceAdvisorRepository.save(advisor2);
        }
    }
}
