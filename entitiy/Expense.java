package uz.pdp.srmserver.entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Expense extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    private double sum;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private PayType payType;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Report report;
}
