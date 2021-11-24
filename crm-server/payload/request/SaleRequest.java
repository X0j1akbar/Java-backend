package uz.pdp.srmserver.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import uz.pdp.srmserver.entitiy.Payment;
import uz.pdp.srmserver.entitiy.ProductWithAmount;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequest {
    private Integer customerId;
    private String description;
    @NonNull
    private List<ProductWithAmount> productWithAmounts;
    @NonNull
    private List<Payment> payments;
}
