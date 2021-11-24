package uz.pdp.exesises.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exesises.entity.template.AbsTemplate;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsTemplate {

    private String name;

    private String description;

    private double price;

    private String photo;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;


}
