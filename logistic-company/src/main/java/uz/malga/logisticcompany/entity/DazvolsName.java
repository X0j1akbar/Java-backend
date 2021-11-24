package uz.malga.logisticcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.malga.logisticcompany.entity.enums.DazvolName;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DazvolsName implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private DazvolName companyName;

    public DazvolsName(DazvolName companyName) {
        this.companyName = companyName;
    }

    @Override
    public String getAuthority() {
       return companyName.name();
    }
}
