package uz.pdp.exesises.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exesises.entity.Customer;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer customerId;
    private Integer productId;
    private int quantity;
    private String productName;
    private Customer customer;
    private Date date;
}
