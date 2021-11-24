package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Defect;

import java.util.UUID;

public interface DefectRepository extends JpaRepository<Defect, UUID> {
    Defect getById(UUID id);
}