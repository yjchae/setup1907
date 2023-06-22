package com.schana.dto;

import lombok.Getter;

@Getter
public enum PeopleEnum {
    PEOPLE_KEY(1)   //신청인
    ,NAME(2)    //이름
    ,MOBILE(3)      //전화번호
    ,GENDER(4)      //성별
    ,AGE(5)     //나이
    ,CHURCH(6)      //소속교회

    ,FIRST_PAY(7)       //1차입금액
    ,SEC_PAY(8)     //2차입금액
    ,COMPLETE_PAY(9)        //입금금액
    ,PAY_DT(10)     //입금일
    ,PAY_STATUS(11)     //입금상태

    ,ROOM_INFO(12)      //방배정정보
    ,REG_DT(13)     //등록일
    ,PEOPLE_KEY_SEC(14)     //대표인

    ,MON(15)        //참석여부 월요일
    ,TUE(16)        //참석여부 화요일
    ,WED(17)        //참석여부 수요일
    ,THU(18)        //참석여부 목요일
    ,FRI(19)        //참석여부 금요일
    ,SAT(20)        //참석여부 토요일

    ,CAR(21)        //자차참석
    ,BUS(22)        //대중교통
    ,BICYCLE(23)    //자전거 및 도보
    ,HOW(24)        //알게된경로
    ,PASTOR(25)     //목회자여부

//    ,NORTH_KOREAN(7)     //탈북민여부
//    ,LAYMAN(8)     //평신도여부
//    ,COMPLETE_PAY(24)        //입금금액
//    ,PAY_DT(25)     //입금일
//    ,PAY_STATUS(26)     //입금상태
//    ,ROOM_INFO(27)      //방배정정보
    ;

    private int indexNum;
    PeopleEnum(int indexNum) {
        this.indexNum = indexNum;
    }

}

