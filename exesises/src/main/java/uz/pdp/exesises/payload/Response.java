package uz.pdp.exesises.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String status;

    private Integer invoice_number;

    private Object payment_details;

    public Response(String status) {
        this.status = status;
    }

    public Response(String status, Object payment_details) {
        this.status = status;
        this.payment_details = payment_details;
    }
}
