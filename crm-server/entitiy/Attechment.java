package uz.pdp.srmserver.entitiy;

import   lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.template.AbsTemplate;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Attechment extends AbsTemplate {


    private String originalName;

    private long size;

    private String contentType;

    private String fileName;

    private String path;

    public Attechment(String originalFilename, String s, int size, String s1) {

    }
}
