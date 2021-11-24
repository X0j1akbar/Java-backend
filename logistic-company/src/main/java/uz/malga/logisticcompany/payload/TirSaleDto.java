package uz.malga.logisticcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.malga.logisticcompany.entity.Company;
import uz.malga.logisticcompany.entity.Dazvol;
import uz.malga.logisticcompany.entity.Tir;
import uz.malga.logisticcompany.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TirSaleDto {

    private List<UUID> tirIds;

    private List<Tir> tirs;

    private long companyId;

    private Company company;

    private String carNumber;

    private String userName;

    private Date date;

    private User customer;
}
