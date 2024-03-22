package com.schana.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Value;

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
    @Value(" ")
    private String name;
    @Value(" ")
    private String mobile;
    @Value(" ")
    private String gender;
    @Value("0")
    private Integer age;
    @Value(" ")
    private String church;
    @Value("0")
    private Integer first_pay;
    @Value("0")
    private Integer sec_pay;
    @Value("0")
    private Integer complete_pay;
    @Value(" ")
    private String pay_dt;
    @Value(" ")
    private String pay_status;
    @Value(" ")
    private String roominfo;
    @Value(" ")
    private String reg_dt;
    @Value(" ")
    private String people_key_sec;
    @Value("TRUE")
    private String mon;
    @Value("TRUE")
    private String tue;
    @Value("TRUE")
    private String wed;
    @Value("TRUE")
    private String thu;
    @Value("TRUE")
    private String fri;
    @Value("TRUE")
    private String sat;
    @Value(" ")
    private String car;
    @Value(" ")
    private String bus;
    @Value(" ")
    private String bicycle;
    @Value(" ")
    private String how;
    @Value(" ")
    private String pastor;
    @Value(" ")
    private String northkorean;
    @Value(" ")
    private String layman;
    @Value(" ")
    private String allday_yn;
    @Value("0")
    private Integer with_adult_man;
    @Value("0")
    private Integer with_adult_woman;
    @Value("0")
    private Integer with_child_man;
    @Value("0")
    private Integer with_child_woman;
    @Value(" ")
    private String pay_yn;
    @Value(" ")
    private String reg_name;
    @Value(" ")
    private String checkinout;
    private String syncyn;
    private String checkoutdt;

}
