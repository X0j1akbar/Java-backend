package uz.malga.logisticcompany.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.malga.logisticcompany.entity.template.AbsTemplate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dazvol extends AbsTemplate {

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    private String dazvolNumber;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private DazvolsName dazvolName;

    private Date fromDate;

    private Date experienceDate;

    private boolean active;


}
