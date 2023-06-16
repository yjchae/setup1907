package com.schana.dao;

import com.schana.dto.RoomDto;
import com.schana.entity.RoomEntity;
import com.schana.entity.RoomMasterEntity;
import com.schana.repository.RoomMasterRepository;
import com.schana.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomDao {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMasterRepository roomMasterRepository;

    /**
     *
     * @param roomnum
     * @return
     */
    public List<RoomEntity> getRoomList(){
        return roomRepository.findAll();
    }

    public int chkroom(long peopleseqno){
        return roomRepository.checkMasterseqno(peopleseqno);
    }

    /**
     * 방정보 조회 master 테이블
     * @return
     */
    public List<RoomMasterEntity> getRoomMasterList(){
        return roomMasterRepository.findAll();
    }

    public List<RoomMasterEntity> getRoom(String roomstatus){
        return roomMasterRepository.findByStatus(roomstatus);
    }

    public RoomMasterEntity getRoomSeqno(long roomstatus){
        return roomMasterRepository.findBySeqno(roomstatus);
    }


    public void saveRoom(RoomEntity roomEntity) {
        roomRepository.save(roomEntity);
    }
}
