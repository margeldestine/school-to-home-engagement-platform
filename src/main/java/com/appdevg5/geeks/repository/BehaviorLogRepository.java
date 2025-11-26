package com.appdevg5.geeks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.appdevg5.geeks.entity.BehaviorLogEntity;

@Repository
public interface BehaviorLogRepository extends JpaRepository<BehaviorLogEntity, Integer> {
}

