package com.schana.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {

    private String dormitory;
    private Integer room_num;
    private Integer max_people;
    private String type;
    private String status;
}
