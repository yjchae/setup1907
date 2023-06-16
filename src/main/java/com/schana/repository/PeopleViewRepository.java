package com.schana.repository;

import com.schana.entity.PeopleViewEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleViewRepository extends JpaRepository<PeopleViewEntity, Long> {

    PeopleViewEntity findBySeqno(long seqno);

//    List<PeopleViewEntity> findAll(Sort sort);
    List<PeopleViewEntity> findAllByOrderByChurchAscPeoplekeyAsc();

}
