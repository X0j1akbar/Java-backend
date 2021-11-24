package uz.pdp.srmserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.User;
import uz.pdp.srmserver.entitiy.enums.PayType;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GivenSalaryDto {

    private UUID id;

    private UUID employeeId;

    private User employee;

    private double sum;

    private PayType payType;

    private boolean approved;

}
