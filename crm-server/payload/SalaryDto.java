package uz.pdp.srmserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.Sale;
import uz.pdp.srmserver.entitiy.ShopKpi;
import uz.pdp.srmserver.entitiy.User;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {

    private UUID id;

    private UUID userId;

    private User user;

    private Date fromDate;

    private Date toDate;

    private ShopKpi shopKpi;

    private List<Sale> sales;

    private double totalSalary;


}
