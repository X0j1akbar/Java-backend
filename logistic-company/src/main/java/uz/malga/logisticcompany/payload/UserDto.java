package uz.malga.logisticcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.malga.logisticcompany.entity.Permission;
import uz.malga.logisticcompany.entity.Role;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Pattern(regexp = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$")
    //+998931234988
    private String phoneNumber;

    private boolean active;
    private Set<Permission> permissions;
    private Integer role;

    private double fixSalary;
    private List<Role> roles;


}
