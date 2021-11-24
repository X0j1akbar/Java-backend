package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Payment;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}