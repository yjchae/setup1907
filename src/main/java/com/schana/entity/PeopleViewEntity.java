package com.schana.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="vw_member")
public class PeopleViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqno;
    private String people_key;
    private String name;
    private String mobile;
    private String gender;
    private Integer age;
    private String church;
    private Integer complete_pay;
    private String pay_dt;
    private String pay_status;
    private String room_info;
    private String allday_yn;
    private String member_status;
    private String day;
}
