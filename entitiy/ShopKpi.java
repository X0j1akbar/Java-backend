package uz.pdp.srmserver.entitiy;

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
public class ShopKpi extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Kpi> kpis;
}
