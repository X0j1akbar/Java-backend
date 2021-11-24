package uz.malga.logisticcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.malga.logisticcompany.entity.template.AbsTemplate;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsTemplate implements UserDetails {

    private String firstName;
    private String lastName;

    //  @Column(unique = true)
    private String phoneNumber;

    private String username;
    private String password;

    private double fixSalary;

    @ManyToMany
    private List<Role> roles;


    private boolean active = true;

    public User(String firstName, String lastName, String phoneNumber, String username, String password, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.roles = roles;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Permission> permissions = new HashSet<>();
        for (Role role : this.roles) {
            permissions.addAll(role.getPermissions());
        }
        return permissions;
    }



    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
