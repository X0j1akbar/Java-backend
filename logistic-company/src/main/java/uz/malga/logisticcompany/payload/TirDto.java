package uz.malga.logisticcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.malga.logisticcompany.entity.Company;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TirDto {

    private UUID id;
    private Company company;

    private String tirNumber;

    private String tirNumber2;

    private Date fromDate;

    private Date experienceDate;

    private int list;

    private boolean active;

    private Long companyId;

}
