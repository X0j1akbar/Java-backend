package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.srmserver.entitiy.enums.RoleName;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role  {
    @Id
    @GeneratedValue
    private Integer id;

//    @Enumerated(EnumType.STRING)
//    private RoleName name;
//

    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Permission> permissions;



    public Role(String roleName,Set<Permission> permissions) {
        this.roleName = roleName;
        this.permissions=permissions;
    }




}
