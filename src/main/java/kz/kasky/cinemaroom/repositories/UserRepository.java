package kz.kasky.cinemaroom.repositories;

import kz.kasky.cinemaroom.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findFirstByUsername(String username);

}
