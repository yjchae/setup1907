package com.schana.dao;

import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import com.schana.repository.PeopleRepository;
import com.schana.repository.PeopleViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleDao {

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private PeopleViewRepository peopleViewRepository;

    public List<PeopleEntity> getPeopleList(){
        return peopleRepository.findAll();
    }
    public List<PeopleViewEntity> getPeopleViewList(){
        return peopleViewRepository.findAll();
    }


}
