package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.SecurityUser;
import kz.kasky.cinemaroom.models.dto.RegistrationDto;
import kz.kasky.cinemaroom.models.entities.Role;
import kz.kasky.cinemaroom.models.entities.User;
import kz.kasky.cinemaroom.repositories.RoleRepository;
import kz.kasky.cinemaroom.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public void saveUser(RegistrationDto registrationDto) {

        if (userRepository.existsUserByUserName(registrationDto.getUserName())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();

        user.setUserName(registrationDto.getUserName());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
//        user.setPassword(registrationDto.getPassword());

        Role userRole = new Role();
        userRole.setName("USER");
        userRole.setUser(user);

        userRepository.save(user);

        roleRepository.save(userRole);
    }

    public User findByUsername(String userName) {

        return userRepository.findByUserName(userName).get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("In the UserDetail Service");

        Optional<User> user = userRepository.findByUserName(username);

        User user1 = user.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

        return new SecurityUser(user1);
    }
}
