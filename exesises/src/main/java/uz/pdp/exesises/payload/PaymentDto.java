package uz.pdp.exesises.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exesises.entity.Invoice;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Integer invoiceId;

    private double amount;

    private Invoice invoice;
    private Date time;

}
