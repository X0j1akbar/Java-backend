package uz.malga.logisticcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.malga.logisticcompany.entity.template.AbsTemplate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tir extends AbsTemplate {

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    private Date fromDate;

    private String tirNumber;

    private Date experienceDate;

    private int list;

    private boolean active;


}
