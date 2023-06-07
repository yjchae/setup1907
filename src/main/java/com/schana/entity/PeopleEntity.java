package com.schana.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="tb_people")
public class PeopleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqno;
    private String people_key;
    private String name;
    private String mobile;
    private String gender;
    private Integer age;
    private String church;
    private Integer first_pay;
    private Integer sec_pay;
    private Integer complete_pay;
    private String pay_dt;
    private String pay_status;
    private String room_info;
    private String reg_dt;
    private String people_key_sec;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String car;
    private String bus;
    private String bicycle;
    private String how;
    private String pastor;
    private String north_korean;
    private String layman;
    private String allday_yn;
    private Integer with_adult_man;
    private Integer with_adult_woman;
    private Integer with_child_man;
    private Integer with_child_woman;
    private String pay_yn;
    private String reg_name;

}
