package com.schana.repository;

import com.schana.entity.AssemblyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 집회 참가 정보
 */
@Repository
public interface AssemblyRepository extends JpaRepository<AssemblyInfoEntity, Long> {

    AssemblyInfoEntity findFirstBy();
}
