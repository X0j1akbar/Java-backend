package uz.malga.logisticcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import uz.malga.logisticcompany.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String s);


    List<User> findAllByUsernameContaining(String search);
}
