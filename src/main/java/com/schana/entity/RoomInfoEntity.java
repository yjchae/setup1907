package com.schana.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="vw_room")
public class RoomInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqno;
    private Integer regpeople;
    private String dormitory;
    private Integer roomnum;
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
