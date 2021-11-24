package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.srmserver.entitiy.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    boolean existsByPhoneNumber(String phoneNumber);

    List<User> findAllByUsernameContaining(String search);
}
