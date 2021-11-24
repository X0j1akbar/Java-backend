package uz.malga.logisticcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.malga.logisticcompany.entity.enums.CompanyName;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private CompanyName companyName;

    public Company(CompanyName companyName) {
        this.companyName = companyName;
    }

    @Override
    public String getAuthority() {
        return companyName.name();
    }
}
