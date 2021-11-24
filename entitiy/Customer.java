package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsNameTemplate;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends AbsNameTemplate {
    private String phoneNumber;
    private String address;
    private Float lon;
    private Float lat;
}
