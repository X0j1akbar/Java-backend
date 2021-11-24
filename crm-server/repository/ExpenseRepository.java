package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Expense;

import java.sql.Timestamp;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    Page<Expense>findAllByCreatedAtBetween(Timestamp createdAt, Timestamp createdAt2, Pageable pageable);
}