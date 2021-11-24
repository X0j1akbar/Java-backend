package uz.pdp.exesises.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exesises.entity.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;

    private String name;

    private String description;

    private double price;

    private String photo;

    private Integer categoryId;
    private Category category;

}
