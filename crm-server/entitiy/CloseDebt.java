package uz.pdp.srmserver.entitiy;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CloseDebt extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private double sum;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Sale> sales;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "closeDebt",cascade = CascadeType.ALL)
    private List<Payment> payments;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Report report;
}
