package com.schana.repository;

import com.schana.entity.RoomMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMasterRepository extends JpaRepository<RoomMasterEntity, Long> {

    List<RoomMasterEntity> findByStatus(String status);

    RoomMasterEntity findBySeqno(long seqno);
}
