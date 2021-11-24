package uz.pdp.exesises.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.exesises.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

}
