package com.schana.dao;

import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import com.schana.entity.RoomEntity;
import com.schana.repository.PeopleRepository;
import com.schana.repository.PeopleViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
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
        return peopleViewRepository.findAll(Sort.by(Sort.Order.asc("church"),Sort.Order.asc("peoplekey")));
    }

    public List<PeopleViewEntity> getPeopleViewListType(String type){
        return peopleViewRepository.findByMemberstatusOrderByPeoplekeyAscRoominfoAsc(type);
    }

    public PeopleViewEntity getPeople(long peopleSeqno) {
        return peopleViewRepository.findBySeqno(peopleSeqno);
    }

    public PeopleEntity getPeopleMaster(long seqno) {
        return peopleRepository.findBySeqno(seqno);
    }

    public PeopleEntity getPeopleMaster(String peoplekey, String name) {
        return peopleRepository.findByPeoplekeyAndName(peoplekey, name);
    }

    public void save(PeopleEntity peopleMaster) {
        peopleRepository.save(peopleMaster);
    }

    public PeopleEntity getPeopleMater(long seqno) {
        return peopleRepository.findBySeqno(seqno);
    }

    public void deletePeople(PeopleEntity peopleEntity){
        peopleRepository.delete(peopleEntity);
    }
}
