package com.deepakcode.hello.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.deepakcode.hello.entity.Bill;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
  Optional<Bill> findByOrderId(String orderId);
}
