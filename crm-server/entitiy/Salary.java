package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Salary extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Date fromDate;
    private Date toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShopKpi shopKpi;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Sale> sales;

    private double totalSalary;

}
