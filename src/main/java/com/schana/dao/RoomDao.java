package com.schana.dao;

import com.schana.dto.RoomDto;
import com.schana.entity.RoomEntity;
import com.schana.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomDao {

    @Autowired
    private RoomRepository roomRepository;

    public List<RoomEntity> getRoomList(int roomnum){
        return roomRepository.findByRoomnum(roomnum);
    }

}
