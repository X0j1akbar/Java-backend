package uz.pdp.srmserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.Kpi;
import uz.pdp.srmserver.entitiy.Shop;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopKpiDto {

    private UUID id;
    private Shop shop;

    private List<Kpi> kpis;
}

