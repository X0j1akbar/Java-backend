package uz.pdp.srmserver.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.Report;
import uz.pdp.srmserver.entitiy.Shop;
import uz.pdp.srmserver.entitiy.enums.PayType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {

    private UUID id;

    private Integer shopId;
    private Shop shop;

    private double sum;

    private String description;

    private PayType payType;

    private UUID reportId;

    private Report report;
}
