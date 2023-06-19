package com.schana.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Data
public class RoomDto {

    private String dormitory;
    private Integer roomnum;
    private Integer maxpeople;
    private String startroom;
    private String endroom;
    private String type;
    private String status;
}
