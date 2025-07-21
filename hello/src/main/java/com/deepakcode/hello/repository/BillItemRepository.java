package com.deepakcode.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.deepakcode.hello.entity.BillItem;

@Repository
public interface BillItemRepository extends JpaRepository<BillItem, Long> {}
