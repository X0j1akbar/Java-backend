package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Attechment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attechment, UUID> {
    Attechment getById(UUID id);
}
