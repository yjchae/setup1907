package com.schana.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="tb_room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqno;
    private Integer roomnum;
    private String date;
    private String peoplekey;
    private String dormitory;
    private String stymd;
    private String edymd;
    private String name;
    private long masterseqno;

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
