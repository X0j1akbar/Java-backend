package uz.pdp.srmserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.srmserver.entitiy.User;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonusDto {

    private UUID id;

    private UUID userId;

    private double bonusSum;

    private String description;

    private boolean approved;

    private User user;
}
