package uz.pdp.exesises.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exesises.entity.template.AbsTemplate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice extends AbsTemplate {

    private double amount;

    private Date issued;

    private Date due;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

}
