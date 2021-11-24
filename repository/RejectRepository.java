package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Reject;

import java.util.UUID;

public interface RejectRepository extends JpaRepository<Reject, UUID> {
    Reject getById(UUID id);
}