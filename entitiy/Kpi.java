package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.enums.RoleName;
import uz.pdp.srmserver.entitiy.template.AbsNameTemplate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Kpi extends AbsNameTemplate {
    private double minSum;
    private double maxSum;
    private double percent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
}
