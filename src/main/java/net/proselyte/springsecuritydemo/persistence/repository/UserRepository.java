package net.proselyte.springsecuritydemo.persistence.repository;

import net.proselyte.springsecuritydemo.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
