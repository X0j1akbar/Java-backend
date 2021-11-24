package uz.pdp.srmserver.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.enums.ReportStatus;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private User approver;

    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.IN_PROGRESS;

    private boolean approved;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "report",cascade = CascadeType.ALL)
    private List<Reject> rejects;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "report",cascade = CascadeType.ALL)
    private List<Sale> sales;


    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "report",cascade = CascadeType.ALL)
    private List<CloseDebt> closeDebts;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "report",cascade = CascadeType.ALL)
    private List<Expense> expenses;
}
