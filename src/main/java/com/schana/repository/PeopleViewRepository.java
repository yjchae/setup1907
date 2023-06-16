package com.schana.repository;

import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleViewRepository extends JpaRepository<PeopleViewEntity, Long> {

//    List<PeopleEntity> findAllBy();

    PeopleViewEntity findBySeqno(long seqno);



}
