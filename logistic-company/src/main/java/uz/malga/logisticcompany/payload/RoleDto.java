package uz.malga.logisticcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.malga.logisticcompany.entity.Permission;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Integer id;
    private String roleName;
    private Set<Permission> permissions;
}
