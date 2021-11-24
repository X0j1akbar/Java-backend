package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsNameTemplate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shop extends AbsNameTemplate {
    private String address;
    private Float lon;
    private Float lat;

    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;
}