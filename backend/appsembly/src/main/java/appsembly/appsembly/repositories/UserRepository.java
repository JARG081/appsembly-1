package appsembly.appsembly.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import appsembly.appsembly.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

    @Query(value = "SELECT u FROM users u WHERE u.email = :identifier OR u.personalCode = :identifier", nativeQuery = true)
    UserEntity findUser(String identifier);

}
