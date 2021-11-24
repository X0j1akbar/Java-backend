package uz.pdp.exesises.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exesises.entity.template.AbsTemplate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")

public class Order extends AbsTemplate {

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Customer customer;

}
