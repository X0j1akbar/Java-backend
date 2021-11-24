package uz.malga.logisticcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.malga.logisticcompany.entity.Role;
import uz.malga.logisticcompany.payload.RoleDto;
import uz.malga.logisticcompany.repository.RoleRepository;


import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();

    }



    public static RoleDto getRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(role.getRoleName());
        roleDto.setId(roleDto.getId());
        return roleDto;
    }


}
