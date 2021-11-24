package uz.pdp.srmserver.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.ProductWithAmount;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefectRequest {
    private List<ProductWithAmount> productWithAmounts;
}
