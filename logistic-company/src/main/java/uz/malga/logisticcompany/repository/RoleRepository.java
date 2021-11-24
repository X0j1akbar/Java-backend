package uz.malga.logisticcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.malga.logisticcompany.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByIdIn(List<Integer> role);
}
