package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsTemplate {
    private String name;
    private String description;
    private double incomePrice;
    private double salePrice;
    private boolean active;
    private boolean expired;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Attechment> photos;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private int norma;


}
