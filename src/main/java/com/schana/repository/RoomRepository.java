package com.schana.repository;

import com.schana.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    List<RoomEntity> findByRoomnum(int roomnum);

    //기존등록된 방정보 체크
    @Query(value = "select count(*) as cnt from tb_room where masterseqno = ?1 ", nativeQuery = true)
    int checkMasterseqno(long peopleseqno);

    List<RoomEntity> findAllByOrderByDormitoryAscRoomnumAsc();

}
