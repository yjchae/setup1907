package com.schana.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@Table(name ="tb_people", indexes = {
        @Index(name="idx__seqno__name", columnList = "seqno, name"),
        @Index(name="idx__seqno__church", columnList = "seqno, church"),
        @Index(name="idx__seqno__church", columnList = "peoplekey, name")
})
public class PeopleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seqno;
    private String peoplekey;
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
    private String roominfo;
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
    private String northkorean;
    private String layman;
    private String allday_yn;
    private Integer with_adult_man;
    private Integer with_adult_woman;
    private Integer with_child_man;
    private Integer with_child_woman;
    private String pay_yn;
    private String reg_name;
    private String checkinout;

}
