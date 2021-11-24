package uz.pdp.srmserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.Attechment;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private UUID id;
    private String name;
    private String description;
    private double incomePrice;
    private double salePrice;
    private boolean active;
    private boolean expired;
    private List<Attechment> photos;
    private UUID photoId;

    private CategoryDto category;
    private Integer categoryId;

    private int norma;


}
