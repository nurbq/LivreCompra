package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.dto.RegistrationDto;
import kz.kasky.cinemaroom.models.entities.Role;
import kz.kasky.cinemaroom.models.entities.User;
import kz.kasky.cinemaroom.repositories.RoleRepository;
import kz.kasky.cinemaroom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();

        user.setUserName(registrationDto.getUserName());
        user.setPassword(registrationDto.getPassword());

        Role role = new Role();
        role.setName("USER");
        role.setUser(user);


        userRepository.save(user);
        roleRepository.save(role);

    }

    public User findByUsername(String userName) {

        return userRepository.findByUserName(userName);
    }
}
