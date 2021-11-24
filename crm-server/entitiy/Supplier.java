package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier extends AbsTemplate {
    private String name;
    private String address;
    private Float lon;
    private Float lat;
    private String phoneNumber;
}
