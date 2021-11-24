package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.enums.PayType;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GivenSalary extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private User employee;

    private double sum;

    @Enumerated(value = EnumType.STRING)
    private PayType payType;

    private boolean approved;
}
