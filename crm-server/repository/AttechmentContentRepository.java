package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Attechment;
import uz.pdp.srmserver.entitiy.AttechmentContent;

import java.util.UUID;

public interface AttechmentContentRepository extends JpaRepository<AttechmentContent, UUID> {
}