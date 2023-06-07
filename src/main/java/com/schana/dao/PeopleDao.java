package com.schana.dao;

import com.schana.entity.PeopleEntity;
import com.schana.entity.RoomEntity;
import com.schana.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleDao {

    @Autowired
    private PeopleRepository peopleRepository;

    public List<PeopleEntity> getRoomList(){
        return peopleRepository.findAll();
    }

}
