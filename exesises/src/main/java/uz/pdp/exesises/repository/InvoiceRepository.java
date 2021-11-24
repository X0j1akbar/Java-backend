package uz.pdp.exesises.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.exesises.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
