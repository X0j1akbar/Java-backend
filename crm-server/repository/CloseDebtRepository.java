package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.CloseDebt;

import java.util.UUID;

public interface CloseDebtRepository extends JpaRepository<CloseDebt, UUID> {
}