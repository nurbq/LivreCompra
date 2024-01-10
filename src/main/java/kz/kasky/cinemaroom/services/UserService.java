package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.dto.RegistrationDto;
import kz.kasky.cinemaroom.models.entities.Role;
import kz.kasky.cinemaroom.models.entities.UserEntity;
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
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(registrationDto.getUserName());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        Role role = new Role();
        role.setName("USER");
        role.setUser(userEntity);


        userRepository.save(userEntity);
        roleRepository.save(role);

    }

    public UserEntity findByUsername(String username) {

        return userRepository.findFirstByUsername(username);
    }


}
