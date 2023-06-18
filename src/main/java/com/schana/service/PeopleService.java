package com.schana.service;


import com.schana.dao.PeopleDao;
import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    @Autowired
    private PeopleDao peopleDao;

    public List<PeopleEntity> getPeopleList() {
        //TODO : DTO 와 entity 간의 데이터 핸들러 추가

        return peopleDao.getPeopleList();
    }

    public List<PeopleViewEntity> getPeopleViewList(){

        return peopleDao.getPeopleViewList();
    }

    public PeopleViewEntity getPeople(long peopleSeqno) {


        return peopleDao.getPeople(peopleSeqno);
    }

    public void updatePeople(PeopleEntity peopleEntity) {

        //사람정보 조회
        PeopleEntity oldpeopleinfo = peopleDao.getPeopleMater(peopleEntity.getSeqno());
        oldpeopleinfo.setComplete_pay(peopleEntity.getComplete_pay());
        oldpeopleinfo.setPay_dt(peopleEntity.getPay_dt());

        peopleDao.save(oldpeopleinfo);
    }
}
