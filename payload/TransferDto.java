package uz.pdp.srmserver.payload;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.ProductWithAmount;
import uz.pdp.srmserver.entitiy.Supplier;
import uz.pdp.srmserver.entitiy.Warehouse;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDto {

    private UUID id;
    private Warehouse fromWarehouse;


    private Warehouse toWarehouse;

    private boolean approved;
    private Supplier supplier;

    private String agentName;
    private String agentPhoneNumber;


    private List<ProductWithAmount> productWithAmounts;

}
