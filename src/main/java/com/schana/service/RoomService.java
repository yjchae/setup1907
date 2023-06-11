package com.schana.service;


import com.schana.dao.RoomDao;
import com.schana.dto.RoomDto;
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

    public List<RoomEntity> getRoomList(int roomnum) {
        //TODO : DTO 와 entity 간의 데이터 핸들러 추가
//        List<RoomDto> roomDtoList = new ArrayList<>();
//
//        List<RoomEntity> roomList = roomDao.getRoomList(roomnum);
//        for(int i =0 ; i<roomList.size(); i++){
//            roomDtoList.set(i, RoomDto.builder().roomEntity(roomList.get(i)).build());
//        }



        return roomDao.getRoomList(roomnum);
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

}
