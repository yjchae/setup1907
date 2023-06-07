package com.schana.service;


import com.schana.dao.PeopleDao;
import com.schana.entity.PeopleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    @Autowired
    private PeopleDao peopleDao;

    public List<PeopleEntity> getRoomList() {
        //TODO : DTO 와 entity 간의 데이터 핸들러 추가

        return peopleDao.getRoomList();
    }
}
