package com.schana.entity;

import com.schana.dto.RoomDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
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

//    public static RoomMasterEntityBuilder builder(RoomDto roomDto){
//        return RoomMasterEntityBuilder()
//                .dormitory(roomDto.getDormitory())
//                .room_num(roomDto.getRoomnum())
//                .max_people(roomDto.getMaxpeople())
//                .type(roomDto.getType())
//                .status(roomDto.getStatus());
//    }
//    @Builder
//    public RoomMasterEntity(RoomDto roomDto){
//        this.dormitory = roomDto.getDormitory();
//        this.room_num = roomDto.getRoomnum();
//        this.max_people = roomDto.getMaxpeople();
//        this.type = roomDto.getType();
//        this.status =roomDto.getStatus();
//    }
}
