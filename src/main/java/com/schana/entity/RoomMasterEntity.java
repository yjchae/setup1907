package com.schana.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="tb_room_master")
public class RoomMasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqno;
    private String dormitory;
    private Integer room_num;
    private Integer max_people;
    private String type;
    private String status;

//    @Builder()
//    public RoomEntity(RoomDto roomDto){
//        this.roomnum = roomDto.getRoomnum();
//        this.date = roomDto.getDate();
//        this.peoplekey = roomDto.getPeoplekey();
//        this.inout = roomDto.getInout();
//        this.dormitory = roomDto.getDormitory();
//        this.max_people = roomDto.getMax_people();
//    }
}
