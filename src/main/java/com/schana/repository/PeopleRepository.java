package com.schana.repository;

import com.schana.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, Long> {

//    List<PeopleEntity> findAllBy();
}
