package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Role;
import uz.pdp.srmserver.entitiy.enums.RoleName;

import java.util.Collection;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleName roleName);
    List<Role> findAllByRoleName(RoleName roleName);

    List<Role> findAllByIdIn(Collection<Integer> id);

}
