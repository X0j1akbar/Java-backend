package uz.pdp.srmserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.srmserver.entitiy.Role;
import uz.pdp.srmserver.service.RoleService;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/all")
    public HttpEntity<List<Role>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRole());
    }


//    @PostMapping("/save")
//    public HttpEntity<ApiResponse> setRoleForUser(@Valid @RequestBody RoleDto roleDto) {
//        ApiResponse apiResponse = roleService.roleSave(roleDto);
//        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Saved") ? 201 : 202 : 409).body(apiResponse);
//
//    }

}
