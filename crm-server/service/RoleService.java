package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Role;
import uz.pdp.srmserver.payload.RoleDto;
import uz.pdp.srmserver.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
@Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();

    }


//    public ApiResponse roleSave(RoleDto roleDto) {
//
//
//
//    }

    public static RoleDto getRoleDto(Role role){
        RoleDto roleDto=new RoleDto();
        roleDto.setRoleName(role.getRoleName());
        roleDto.setId(roleDto.getId());
        return roleDto;
    }


}
