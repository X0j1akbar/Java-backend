package uz.pdp.exesises.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exesises.entity.template.AbsTemplate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends AbsTemplate {

   private Date time;

   private double amount;

   @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;
}
