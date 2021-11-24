package uz.pdp.srmserver.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.enums.PayStatus;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class
Sale extends AbsTemplate {
    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Report report;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sale",cascade = CascadeType.ALL)
    private List<Payment> payments;

    @Enumerated(value = EnumType.STRING)
    private PayStatus payStatus;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sale",cascade = CascadeType.ALL)
    private List<ProductWithAmount> productWithAmounts;

    private String description;




}
