package com.schana.repository;

import com.schana.entity.RoomInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomInfoRepository extends JpaRepository<RoomInfoEntity, Long> {

    List<RoomInfoEntity> findAllByOrderByStatusAscRoomnumAsc();

    List<RoomInfoEntity> findByTypeOrTypeOrderByStatusAscRoomnumAsc(String type1, String type2);

    List<RoomInfoEntity> findByTypeOrderByStatusAscRoomnumAsc(String type);
}
