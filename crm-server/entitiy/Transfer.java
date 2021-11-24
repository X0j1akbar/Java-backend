package uz.pdp.srmserver.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Transfer extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse fromWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse toWarehouse;

    private boolean approved;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    private String agentName;
    private String agentPhoneNumber;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "transfer",cascade = CascadeType.ALL)
    private List<ProductWithAmount> productWithAmounts;


}
