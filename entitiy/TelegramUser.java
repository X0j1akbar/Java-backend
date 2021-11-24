package uz.pdp.srmserver.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TelegramUser {
    @Id
    @GeneratedValue
    private Integer id;

    private Long chatId;

    private String botState;

    private String phoneNumber;
    private String address;
    private Double lon;
    private Double lat;

}
