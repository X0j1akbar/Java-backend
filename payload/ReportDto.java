package uz.pdp.srmserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.*;
import uz.pdp.srmserver.entitiy.enums.ReportStatus;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private UUID id;

    private Shop shop;

    private User approver;

    private ReportStatus status = ReportStatus.IN_PROGRESS;

    private boolean approved;

    private List<Reject> rejects;


    private List<Sale> sales;


    private List<CloseDebt> closeDebts;

    private List<Expense> expenses;


}
