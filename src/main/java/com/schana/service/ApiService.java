package com.schana.service;


import com.schana.dao.ApiDao;
import com.schana.dao.AssemblyDao;
import com.schana.dao.PeopleDao;
import com.schana.dto.PeopleEnum;
import com.schana.entity.AssemblyInfoEntity;
import com.schana.entity.PeopleEntity;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ApiService {

    @Autowired
    private ApiDao apiDao;

    @Autowired
    private PeopleDao peopleDao;

    /**
     * 구글 문서와 참석자 동기화
     */
    public void syncPeople() throws IOException {

        JSONObject jsonObj = new JSONObject(getApiData());
        JSONArray jsonArray =(JSONArray) jsonObj.get("values");

        for(int i=1 ; i < jsonArray.length() ; i++){
            JSONArray valueArr = (JSONArray) jsonArray.get(i);

            String peopleKey = (String)valueArr.get(PeopleEnum.PEOPLE_KEY.getIndexNum());
            String name = (String)valueArr.get(PeopleEnum.NAME.getIndexNum());

            PeopleEntity peopleOldInfo = peopleDao.getPeopleMaster(peopleKey,name);

            //기존 등록된 인원은 재등록 하지 않음
            if(peopleOldInfo == null){
                peopleDao.save(setPeopleData(valueArr));
            }

        }
    }

    /**
     * 참가신청한 정보 가져오기
     * @return
     * @throws IOException
     */
    private String getApiData() throws IOException{
        String stringURL = "https://sheets.googleapis.com/v4/spreadsheets/1hbbwUl0t--HRmzQT2E4x982vWhJECYXdShY76leGWIk/values/A1:AB10000001?key=AIzaSyB6WH0-v6d-EEdo4NK6-qlHG7Ml4jxgL5c";
//        String stringURL = "https://sheets.googleapis.com/v4/spreadsheets/1VpYEm5N4zRY88WyfG7SjHU5XjjA6CZHi-DS-OvuAHow/values/A1:Z10000001?key=AIzaSyDJIksYLqH9tDL30WfLBQfVmZg3dattW18";
        URL url = new URL(stringURL);
        String line;
        StringBuilder sb = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb.toString();
    }

    /**
     * 참석자 개별 정보 세팅 - 저장용
     */
    private PeopleEntity setPeopleData(JSONArray valueArr){
        PeopleEntity people = new PeopleEntity();

        people.setPeoplekey((String)valueArr.get(PeopleEnum.PEOPLE_KEY.getIndexNum()));
        people.setName((String)valueArr.get(PeopleEnum.NAME.getIndexNum()));
        people.setMobile((String)valueArr.get(PeopleEnum.MOBILE.getIndexNum()));
        people.setGender((String)valueArr.get(PeopleEnum.GENDER.getIndexNum()));
        people.setAge(Integer.parseInt((String)valueArr.get(PeopleEnum.AGE.getIndexNum())));
        people.setChurch((String)valueArr.get(PeopleEnum.CHURCH.getIndexNum()));

//        people.setNorth_korean((String)valueArr.get(PeopleEnum.NORTH_KOREAN.getIndexNum()));
//        people.setLayman((String)valueArr.get(PeopleEnum.LAYMAN.getIndexNum()));
//        String completePay = (String)valueArr.get(PeopleEnum.COMPLETE_PAY.getIndexNum());

        String completePay = ((String) valueArr.get(PeopleEnum.COMPLETE_PAY.getIndexNum())).isEmpty() ? "0" : (String) valueArr.get(PeopleEnum.COMPLETE_PAY.getIndexNum());

        people.setFirst_pay(Integer.parseInt((String)valueArr.get(PeopleEnum.FIRST_PAY.getIndexNum())));
        people.setSec_pay(Integer.parseInt((String)valueArr.get(PeopleEnum.SEC_PAY.getIndexNum())));
        people.setComplete_pay(Integer.parseInt(completePay));
        people.setPay_dt((String)valueArr.get(PeopleEnum.PAY_DT.getIndexNum()));
        people.setPay_status((String)valueArr.get(PeopleEnum.PAY_STATUS.getIndexNum()));
        people.setReg_dt((String)valueArr.get(PeopleEnum.REG_DT.getIndexNum()));
        people.setPeople_key_sec((String)valueArr.get(PeopleEnum.PEOPLE_KEY_SEC.getIndexNum()));

        String mon = (String)valueArr.get(PeopleEnum.MON.getIndexNum());
        String tue = (String)valueArr.get(PeopleEnum.TUE.getIndexNum());
        String wed = (String)valueArr.get(PeopleEnum.WED.getIndexNum());
        String thu = (String)valueArr.get(PeopleEnum.THU.getIndexNum());
        String fri = (String)valueArr.get(PeopleEnum.FRI.getIndexNum());
        String sat = (String)valueArr.get(PeopleEnum.SAT.getIndexNum());

        people.setMon(mon);
        people.setTue(tue);
        people.setWed(wed);
        people.setThu(thu);
        people.setFri(fri);
        people.setSat(sat);

        if(mon.equals("TRUE")
                && tue.equals("TRUE")
                && wed.equals("TRUE")
                && thu.equals("TRUE")
                && fri.equals("TRUE")
                && sat.equals("TRUE")
        ){
            people.setAllday_yn("전참");
        }else{
            people.setAllday_yn("부분참석");
        }

        people.setCar((String)valueArr.get(PeopleEnum.CAR.getIndexNum()));
        people.setBus((String)valueArr.get(PeopleEnum.BUS.getIndexNum()));
        people.setBicycle((String)valueArr.get(PeopleEnum.BICYCLE.getIndexNum()));
        people.setHow((String)valueArr.get(PeopleEnum.HOW.getIndexNum()));

        people.setPastor((String)valueArr.get(PeopleEnum.PASTOR.getIndexNum()));
        people.setNorthkorean((String)valueArr.get(PeopleEnum.NORTH_KOREAN.getIndexNum()));
        people.setLayman((String)valueArr.get(PeopleEnum.LAYMAN.getIndexNum()));

        return people;
    }

    public void setPeople(PeopleEntity peopleEntity) {

        peopleDao.save(peopleEntity);
    }
}
