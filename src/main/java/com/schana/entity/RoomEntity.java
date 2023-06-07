package com.schana.entity;

import com.schana.dto.RoomDto;
import jakarta.persistence.*;
import lombok.Builder;
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
    private String inout;
    private String dormitory;
    private Integer max_people;

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
