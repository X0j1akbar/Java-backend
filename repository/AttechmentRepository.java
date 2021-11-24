package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Attechment;

import java.util.UUID;

public interface AttechmentRepository extends JpaRepository<Attechment, UUID> {
}