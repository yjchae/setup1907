package com.schana.repository;

import com.schana.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 집회 참가 정보
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    /**
     * id / pw로 관리자 정보 확인
     * @param id
     * @param password
     * @return
     */
    MemberEntity findByIdAndPassword(String id, String password);
}
