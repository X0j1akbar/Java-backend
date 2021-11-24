package uz.pdp.srmserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KpiDto {

    private Integer id;
    private String name;
    private String description;
    private boolean active;
    private double minSum;
    private double maxSum;
    private double percent;
    private Integer roleId;
    private RoleDto role;
}
