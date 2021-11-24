package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class AttechmentContent extends AbsTemplate {

    @Column(nullable = false)
    private byte[] bytes;

    @OneToOne(optional = false)
    private Attechment attechment;
}
