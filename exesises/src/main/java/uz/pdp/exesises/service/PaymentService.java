package uz.pdp.exesises.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.exesises.entity.Payment;
import uz.pdp.exesises.payload.PaymentDto;
import uz.pdp.exesises.payload.Response;
import uz.pdp.exesises.repository.InvoiceRepository;
import uz.pdp.exesises.repository.PaymentRepository;

import java.sql.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    public Response save(PaymentDto dto){
        Payment payment=new Payment();
        try {
            payment.setAmount(1);
            payment.setTime(new Date(new java.util.Date().getTime()));
            payment.setInvoice(invoiceRepository.getById(dto.getInvoiceId()));
            paymentRepository.save(payment);
            return new Response("SUCCESS",paymentRepository.getById(payment.getId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response("FAILED");
    }

    public PaymentDto getById(Integer id){
        return getPaymentDto(paymentRepository.getById(id));
    }

    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    public PaymentDto getPaymentDto(Payment payment){
        PaymentDto dto=new PaymentDto();

        dto.setAmount(payment.getAmount());
        dto.setInvoice(payment.getInvoice());
        dto.setTime(payment.getTime());
        return dto;
    }

}
