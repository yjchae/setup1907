package com.schana.service;


import com.schana.dao.PeopleDao;
import com.schana.dao.RoomDao;
import com.schana.dto.RoomDto;
import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import com.schana.entity.RoomEntity;
import com.schana.entity.RoomMasterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomDao roomDao;
    @Autowired
    private PeopleDao peopleDao;

    public List<RoomEntity> getRoomList() {
        //TODO : DTO 와 entity 간의 데이터 핸들러 추가
//        List<RoomDto> roomDtoList = new ArrayList<>();
//
//        List<RoomEntity> roomList = roomDao.getRoomList(roomnum);
//        for(int i =0 ; i<roomList.size(); i++){
//            roomDtoList.set(i, RoomDto.builder().roomEntity(roomList.get(i)).build());
//        }



        return roomDao.getRoomList();
    }

    public List<RoomMasterEntity> getRoomMasterList(String roomstatus) {
        List<RoomMasterEntity> roomlist = new ArrayList<>();

        if(roomstatus.isEmpty()){
            roomlist = roomDao.getRoomMasterList();
        }else{
            roomlist = roomDao.getRoom(roomstatus);
        }

        return roomlist;
    }

    public String saveRoom(String roomSeqno, String peopleSeqno) {
        //사람정보 세팅 start
        long pseqno = Long.parseLong(peopleSeqno);

        PeopleViewEntity people = peopleDao.getPeople(pseqno);
        PeopleEntity peopleMaster = peopleDao.getPeopleMaster(pseqno);

        String day = people.getDay();
        String firstday = getdate(String.valueOf(day.charAt(0)));
        String lastday = getdate(String.valueOf(day.charAt(day.length()-1)));

        //1. 기존 등록된인원인지 체크
//        roomDao.chkroom(pseqno);

        RoomEntity roomEntity =  new RoomEntity();

        roomEntity.setPeoplekey(people.getPeople_key());
        roomEntity.setName(people.getName());
        roomEntity.setStymd(firstday);
        roomEntity.setEdymd(lastday);
        roomEntity.setDate(firstday);
        roomEntity.setMasterseqno(people.getSeqno());
        //사람정보 세팅 end

        //방정보 세팅
        RoomMasterEntity roomMaster = roomDao.getRoomSeqno(Long.parseLong(roomSeqno));
        roomEntity.setRoomnum(roomMaster.getRoom_num());
        roomEntity.setDormitory(roomMaster.getDormitory());

        String result = "";
        if(roomMaster.getType().equals(people.getMember_status())){
            roomDao.saveRoom(roomEntity);

            peopleMaster.setRoom_info(roomMaster.getDormitory()+roomMaster.getRoom_num());
            peopleDao.save(peopleMaster);

            result = "저장 되었습니다.";
        }else{
            result = "방 타입이 잘못 되었습니다.";
        }

        return result;
    }

    public String getdate(String date){
        String day = "";
        switch (date){
            case "월" :
                day = "20230710";
                break;
            case "화" :
                day = "20230711";
                break;
            case "수" :
                day = "20230712";
                break;
            case "목" :
                day = "20230713";
                break;
            case "금" :
                day = "20230714";
                break;
            case "토" :
                day = "20230715";
                break;
        }


        return day;
    }
}
