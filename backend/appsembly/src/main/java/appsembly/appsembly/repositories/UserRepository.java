package appsembly.appsembly.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import appsembly.appsembly.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE personal_code = :identifier", nativeQuery = true)
    Optional<UserEntity> findUser(String identifier);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<UserEntity> findAllUsers();

}
