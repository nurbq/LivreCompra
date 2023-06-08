package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.entities.UserEntity;
import kz.kasky.cinemaroom.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findFirstByUsername(username);

        if (userEntity != null) {
            User authUser = new User(
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    userEntity.getAuthorities().stream().map((role -> new SimpleGrantedAuthority(role.getName()))).toList()
            );
            return authUser;
        }else {
            throw new UsernameNotFoundException("Invalid username or password");
        }

    }
}
