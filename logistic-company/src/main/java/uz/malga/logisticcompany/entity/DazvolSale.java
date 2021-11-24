package uz.malga.logisticcompany.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.malga.logisticcompany.entity.template.AbsTemplate;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DazvolSale extends AbsTemplate {

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dazvol> dazvols;

    private String carNumber;

    private String userName;

    private Date date;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;

}
