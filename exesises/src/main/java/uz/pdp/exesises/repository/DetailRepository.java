package uz.pdp.exesises.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.exesises.entity.Detail;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
    Detail getDetailByOrderId(Integer order_id);
}
