package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.dto.RegistrationDto;
import kz.kasky.cinemaroom.models.entities.Role;
import kz.kasky.cinemaroom.models.entities.User;
import kz.kasky.cinemaroom.repositories.RoleRepository;
import kz.kasky.cinemaroom.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    public void saveUser(RegistrationDto registrationDto) {

        if (userRepository.existsUserByUserName(registrationDto.getUserName())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();

        user.setUserName(registrationDto.getUserName());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        Role userRole = new Role();
        userRole.setName("USER");
        userRole.setUser(user);

        userRepository.save(user);

        roleRepository.save(userRole);
    }

    public User findByUsername(String userName) {

        return userRepository.findByUserName(userName).get();
    }


}
