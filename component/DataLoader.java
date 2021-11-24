package uz.pdp.srmserver.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.srmserver.entitiy.Permission;
import uz.pdp.srmserver.entitiy.Role;
import uz.pdp.srmserver.entitiy.User;
import uz.pdp.srmserver.entitiy.enums.PermissionName;
import uz.pdp.srmserver.entitiy.enums.RoleName;
import uz.pdp.srmserver.repository.PermissionRepository;
import uz.pdp.srmserver.repository.RoleRepository;
import uz.pdp.srmserver.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PermissionRepository permissionRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            List<Permission> permissions=new ArrayList<>();
            for (PermissionName permissionName : PermissionName.values()) {
                permissions.add(new Permission(permissionName));
            }
            permissionRepository.saveAll(permissions);
            Role superAdmin =roleRepository.save( new Role("ROLE_ADMIN", new HashSet<>(permissionRepository.findAll())));
            Role manager =roleRepository.save( new Role("ROLE_MANAGER", new HashSet<>(permissionRepository.findAllByIdIn(Collections.singleton(9L)))));
            Role customer = roleRepository.save(new Role("ROLE_CUSTOMER", new HashSet<>(permissionRepository.findAllByIdIn(Collections.singleton(2L)))));
            userRepository.save(
                    new User(
                            "admin",
                            "admin",
                            "+998991234567",
                            "admin",
                            passwordEncoder.encode("admin"),
                            Collections.singletonList(superAdmin)

                    )
            );
            userRepository.save(
                    new User(
                            "manager",
                            "manager",
                            "+9989912388987",
                            "manager",
                            passwordEncoder.encode("manager"),
                            Collections.singletonList(manager)

                    )
            );
            userRepository.save(
                    new User(
                            "customer",
                            "customer",
                            "+998991289897",
                            "customer",
                            passwordEncoder.encode("customer"),
                            Collections.singletonList(customer)

                    )
            );
        }
    }
}
