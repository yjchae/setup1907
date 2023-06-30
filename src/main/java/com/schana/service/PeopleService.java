package com.schana.service;


import com.schana.dao.PeopleDao;
import com.schana.dto.PeopleDto;
import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PeopleService {

    @Autowired
    private PeopleDao peopleDao;

    public List<PeopleEntity> getPeopleList() {
        //TODO : DTO 와 entity 간의 데이터 핸들러 추가

        return peopleDao.getPeopleList();
    }

    /**
     * 일자별 참석인원
     * @return
     */
    public PeopleDto getPeopleDayCnt() {
        PeopleDto peopleDto = new PeopleDto();
        List<PeopleEntity> peoplelist = peopleDao.getPeopleList();

        peopleDto.setMoncount((int) peoplelist.stream().filter(w->w.getMon().contains("TRUE")).count());
        peopleDto.setTuecount((int) peoplelist.stream().filter(w->w.getTue().contains("TRUE")).count());
        peopleDto.setWedcount((int) peoplelist.stream().filter(w->w.getWed().contains("TRUE")).count());
        peopleDto.setThucount((int) peoplelist.stream().filter(w->w.getThu().contains("TRUE")).count());
        peopleDto.setFricount((int) peoplelist.stream().filter(w->w.getFri().contains("TRUE")).count());
        peopleDto.setSatcount((int) peoplelist.stream().filter(w->w.getSat().contains("TRUE")).count());

        return peopleDto;
    }

    public List<PeopleViewEntity> getPeopleViewList(String type){
        if("관리자".equals(type)){
            return peopleDao.getPeopleViewList();
        }else{
            return peopleDao.getPeopleViewListType(type);
        }
    }

    public PeopleViewEntity getPeople(long peopleSeqno) {


        return peopleDao.getPeople(peopleSeqno);
    }

    public void updatePeople(PeopleEntity peopleEntity) {
        //사람정보 조회
        PeopleEntity oldpeopleinfo = peopleDao.getPeopleMater(peopleEntity.getSeqno());
        oldpeopleinfo.setComplete_pay(peopleEntity.getComplete_pay());
        oldpeopleinfo.setPay_dt(peopleEntity.getPay_dt());

        if(oldpeopleinfo.getFirst_pay().equals(peopleEntity.getComplete_pay())
            || oldpeopleinfo.getSec_pay().equals(peopleEntity.getComplete_pay()) ){
            oldpeopleinfo.setPay_status("입금완료");
        }

        peopleDao.save(oldpeopleinfo);
    }

    public void updatePayStatus(String pseqnoArr) {

        String[] rseqnoArr = pseqnoArr.split(",");
        for(String rseqno : rseqnoArr){
            PeopleEntity oldpeopleinfo = peopleDao.getPeopleMater(Long.parseLong(rseqno));

            oldpeopleinfo.setPay_status("입금완료");
            peopleDao.save(oldpeopleinfo);
        }

    }
}
