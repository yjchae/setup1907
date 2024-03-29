package com.schana.service;


import com.schana.dao.PeopleDao;
import com.schana.dao.RoomDao;
import com.schana.dto.RoomDto;
import com.schana.entity.*;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomDao roomDao;
    @Autowired
    private PeopleDao peopleDao;

    public List<RoomEntity> getRoomList(String type) {
        //TODO : DTO 와 entity 간의 데이터 핸들러 추가
//        List<RoomDto> roomDtoList = new ArrayList<>();
//
//        List<RoomEntity> roomList = roomDao.getRoomList(roomnum);
//        for(int i =0 ; i<roomList.size(); i++){
//            roomDtoList.set(i, RoomDto.builder().roomEntity(roomList.get(i)).build());
//        }
        return roomDao.getRoomList();

    }

    public List<RoomMasterEntity> getRoomMasterList(String roomstatus) {
        List<RoomMasterEntity> roomlist = new ArrayList<>();

        if(roomstatus.isEmpty()){
            roomlist = roomDao.getRoomMasterList();
        }else{
            roomlist = roomDao.getRoom(roomstatus);
        }

        return roomlist;
    }

    /**
     *  방정보 조회
     * @param
     * @return
     */
    public List<RoomInfoEntity> getRoomInfoList(String type) {
        if("관리자".equals(type)){
            return roomDao.getRoomInfoList();
        }else{
            return roomDao.getRoomInfoListByType(type);
        }
    }

    /**
     *  방정보 조회
     * @param
     * @return
     */
    public List<RoomInfoEntity> getRoomTypeList(String type) {
        if("관리자".equals(type)){
            return roomDao.getRoomTypeList("한국성도","탈북민");
        }else{
            return roomDao.getRoomInfoListByType(type);
        }
    }


    /**
     * 방배정 저장
     * @param roomSeqno 방 seqno
     * @param peopleSeqno 사람 seqno
     * @param push
     * @param peopleSeqnoArr    다중 선택 seqno
     * @return
     */
    public String saveRoom(String roomSeqno, String peopleSeqno, String push, String peopleSeqnoArr) {
        //사람정보 세팅 start
        String result = "";
        if(peopleSeqnoArr.isEmpty()){
            result = saveRoomDetail(roomSeqno, peopleSeqno, push);
        }else{
            String[] pseqnoArr = peopleSeqnoArr.split(",");
            for(String pseqno : pseqnoArr){
                result = saveRoomDetail(roomSeqno, pseqno, push);
            }
        }

        return result;
    }

    private String saveRoomDetail(String roomSeqno,String peopleSeqno,String push){

        long pseqno = Long.parseLong(peopleSeqno);

        PeopleViewEntity people = peopleDao.getPeople(pseqno);
        PeopleEntity peopleMaster = peopleDao.getPeopleMaster(pseqno);

        String day = people.getDay();
        String firstday = getdate(String.valueOf(day.charAt(0)));
        String lastday = getdate(String.valueOf(day.charAt(day.length()-1)));

        //1. 기존 등록된인원인지 체크
        RoomEntity oldRoomInfo = roomDao.getRoomInfoPeople(pseqno);

        RoomEntity roomEntity =  new RoomEntity();

        roomEntity.setPeoplekey(people.getPeoplekey());
        roomEntity.setName(people.getName());
        roomEntity.setStymd(firstday);
        roomEntity.setEdymd(lastday);
        roomEntity.setDate(firstday);
        roomEntity.setMasterseqno(people.getSeqno());
        //사람정보 세팅 end

        //방정보 세팅
        RoomMasterEntity roomMaster = roomDao.getRoomSeqno(Long.parseLong(roomSeqno));
        roomEntity.setRoomnum(roomMaster.getRoomnum());
        roomEntity.setDormitory(roomMaster.getDormitory());

        String result = "";


        //방타입이 다르거나, 빈방 인경우 바로 저장 아닌경우 한번더 물음
        if((roomMaster.getType().equals(people.getMemberstatus())
                && roomDao.countRoom(roomEntity) == 0)
                || push.equals("push")
                ){
            if(oldRoomInfo == null){
                roomDao.saveRoom(roomEntity);
            }else{
                oldRoomInfo.setRoomnum(roomMaster.getRoomnum());
                oldRoomInfo.setDormitory(roomMaster.getDormitory());
                roomDao.saveRoom(oldRoomInfo);
            }

            peopleMaster.setRoominfo(roomMaster.getDormitory()+roomMaster.getRoomnum());
            peopleMaster.setCheckinout("입실");
            peopleDao.save(peopleMaster);

            result = "success";
        }else{
            if(roomDao.countRoom(roomEntity)>0){
                result = "overcount";
            }else{
                result = "else";
            }
        }

        return result;
    }

    public String getdate(String date){
        String day = "";
        switch (date){
            case "월" :
//                day = "20230710";
                day = "20240108";
                break;
            case "화" :
//                day = "20230711";
                day = "20240109";
                break;
            case "수" :
//                day = "20230712";
                day = "20240110";
                break;
            case "목" :
//                day = "20230713";
                day = "20240111";
                break;
            case "금" :
//                day = "20230714";
                day = "20240112";
                break;
            case "토" :
//                day = "20230715";
                day = "20240113";
                break;
        }


        return day;
    }

    public String deleteRoom(String seqno, String peoplekey, String name) {
        RoomEntity roominfo = roomDao.getRoomInfo(Long.parseLong(seqno));
        roomDao.deleteRoom(roominfo);

        PeopleEntity people = peopleDao.getPeopleMaster(peoplekey,name);
        people.setCheckinout("퇴실");
        String now = DateTimeFormatter.ofPattern("MM/dd HH:mm").format(LocalDateTime.now());
        people.setCheckoutdt(now);

        peopleDao.save(people);
        return "success";
    }

    public String multiDeleteRoom(String seqnoArr) {
        String[] rseqnoArr = seqnoArr.split(",");
        for(String seqno : rseqnoArr){
            RoomEntity roominfo = roomDao.getRoomInfo(Long.parseLong(seqno));
            roomDao.deleteRoom(roominfo);

            PeopleEntity people = peopleDao.getPeopleMater(roominfo.getMasterseqno());
            people.setCheckinout("퇴실");
            peopleDao.save(people);
        }
        return "success";
    }

    public void createRoom(RoomDto roomDto) {
        int startRoom = Integer.parseInt(roomDto.getStartroom());
        int endRoom = Integer.parseInt(roomDto.getEndroom());
        roomDto.setStatus("Y");

        //시작방번호 종료방번호 차이로 방 생성
        while (startRoom <= endRoom){
            int breaknum=0;
            if(breaknum >100){
                break;
            }

            RoomMasterEntity oldRoom = roomDao.getRoomMaster(roomDto.getDormitory(),startRoom);

            if(roomDto.getType().equals("고장난방")){
                roomDto.setStatus("N");
            }

            //방정보 저장
            if(oldRoom ==null){
                RoomMasterEntity roomMasterEntity = RoomMasterEntity.builder()
                        .dormitory(roomDto.getDormitory())
                        .roomnum(startRoom)
                        .maxpeople(roomDto.getMaxpeople())
                        .type(roomDto.getType())
                        .status(roomDto.getStatus())
                        .build();

                roomDao.creatRoom(roomMasterEntity);
            }else{
                oldRoom.setType(roomDto.getType());
                oldRoom.setMaxpeople(roomDto.getMaxpeople());
                oldRoom.setStatus(roomDto.getStatus());

                roomDao.creatRoom(oldRoom);
            }

            startRoom++;
            breaknum++;

        }
    }

    public void deleteRoomMaster(String roomSeqnoArr) {

        String[] rseqnoArr = roomSeqnoArr.split(",");
        for(String rseqno : rseqnoArr){
            roomDao.deleteRoomMater(Long.parseLong(rseqno));
        }
    }

    /**
     * 참석자 지난방 취소 처리
     */
    public void deleteOldRoom() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String edymd = now.format(formatter);

        List<RoomEntity> roomList =  roomDao.getRoomListEndymd(edymd);

        for(RoomEntity room : roomList){
            roomDao.deleteRoom(room);
        }

    }

}
