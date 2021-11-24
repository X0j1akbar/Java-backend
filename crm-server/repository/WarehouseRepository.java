package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}