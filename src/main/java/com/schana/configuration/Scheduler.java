package com.schana.configuration;

import com.schana.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {
    @Autowired
    private RoomService roomService;

//    @Scheduled(cron = "0 0 9 * * *")
//    public void run(){
//        //기간 지난방 제거
//        roomService.deleteOldRoom();
//    }
}
