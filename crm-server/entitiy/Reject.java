package uz.pdp.srmserver.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reject extends AbsTemplate {
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    private boolean approved;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "reject",cascade = CascadeType.ALL)
    private List<ProductWithAmount> productWithAmounts;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Report report;
}
