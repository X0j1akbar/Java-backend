package uz.pdp.srmserver.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

    private Integer id;

    private String phoneNumber;

    private String address;

    private String description;
    private String name;
    private boolean active;

    private Float lon;
    private Float lat;
}
