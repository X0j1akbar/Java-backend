package uz.malga.logisticcompany.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.malga.logisticcompany.entity.*;
import uz.malga.logisticcompany.entity.enums.CompanyName;
import uz.malga.logisticcompany.entity.enums.DazvolName;
import uz.malga.logisticcompany.entity.enums.PermissionName;
import uz.malga.logisticcompany.repository.*;


import java.util.*;

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

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DazvolsNameRepository dazvolsNameRepository;
    @Value("${spring.datasource.initialization-mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            List<Permission> permissions=new ArrayList<>();
            for (PermissionName permissionName : PermissionName.values()) {
                permissions.add(new Permission(permissionName));
            }
            permissionRepository.saveAll(permissions);
            List<Company> companies=new ArrayList<>();
            for (CompanyName companyName : CompanyName.values()) {
               companies.add(new Company(companyName));
            }
            List<DazvolsName> dazvolsNames=new ArrayList<>();
            for (DazvolName dazvolName : DazvolName.values()) {
                dazvolsNames.add(new DazvolsName(dazvolName));
            }
            dazvolsNameRepository.saveAll(dazvolsNames);
            companyRepository.saveAll(companies);

            Role superAdmin =roleRepository.save( new Role("ROLE_ADMIN", new HashSet<>(permissionRepository.findAll())));
            Role manager =roleRepository.save( new Role("ROLE_MANAGER",new HashSet<>(permissionRepository.findAll())));
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
        }
    }
}
