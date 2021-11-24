package uz.malga.logisticcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.malga.logisticcompany.entity.enums.PermissionName;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PermissionName permissionName;

    public Permission(PermissionName permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String getAuthority() {
        return permissionName.name();
    }
}
