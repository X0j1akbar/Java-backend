package uz.pdp.exesises.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.exesises.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
