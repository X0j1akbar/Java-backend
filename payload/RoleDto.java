package uz.pdp.srmserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.Permission;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Integer id;
    private String roleName;
    private Set<Permission> permissions;
}
