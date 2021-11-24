package uz.pdp.srmserver.entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductWithAmount extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Date expireDate;

    private double price;

    private int amount;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Transfer transfer;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Sale sale;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Reject reject;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Defect defect;
}
