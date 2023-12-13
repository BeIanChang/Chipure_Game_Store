package com.ianchang.chipure.repository;

import com.ianchang.chipure.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectRepository extends JpaRepository<Collect, Integer> {

    List<Collect> findByUserId(Integer userId);

    Collect findByUserIdAndProductId(int userId, int productId);
}
