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
public class DazvolDto {

    private Company company;

    private Long companyId;

    private String dazvolNumber;

    private String dazvolNumber2;

    private Date date;

    private Date experienceDate;

    private String name;

    private Long dazvolNameId;

    private boolean active;

    private UUID id;


}
