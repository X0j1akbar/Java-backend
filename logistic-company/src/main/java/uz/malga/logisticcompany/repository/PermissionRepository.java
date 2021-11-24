package uz.malga.logisticcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.malga.logisticcompany.entity.Permission;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission,Long> {

}
