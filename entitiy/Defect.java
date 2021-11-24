package uz.pdp.srmserver.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Defect extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "defect",cascade = CascadeType.ALL)
    private List<ProductWithAmount> productWithAmounts;

}
