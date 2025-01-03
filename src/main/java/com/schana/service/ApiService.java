package com.schana.service;


import com.schana.dao.ApiDao;
import com.schana.dao.PeopleDao;
import com.schana.dto.PeopleEnum;
import com.schana.entity.PeopleEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ApiService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApiDao apiDao;

    @Autowired
    private PeopleDao peopleDao;

    /**
     * 구글 문서와 참석자 동기화
     *
     * @return
     */
    public String syncPeople() throws IOException {
        String result = "";
        JSONObject jsonObj = new JSONObject(getApiData());
        JSONArray jsonArray =(JSONArray) jsonObj.get("values");

        for(int i=1 ; i < jsonArray.length() ; i++){
            JSONArray valueArr = (JSONArray) jsonArray.get(i);
            String peopleKey = (String)valueArr.get(PeopleEnum.PEOPLE_KEY.getIndexNum());
            String name = (String)valueArr.get(PeopleEnum.NAME.getIndexNum());

            try{
                if(!peopleKey.isEmpty()){

                    PeopleEntity peopleOldInfo = peopleDao.getPeopleMaster(peopleKey,name);
                    PeopleEntity people = setPeopleData(valueArr);

                    if(peopleOldInfo == null) {
                        //신규 참석자 등록
                        peopleDao.save(people);

                    }else{
                        //기존 참석자 업데이트
                        peopleOldInfo.setAllday_yn(people.getAllday_yn());
                        peopleOldInfo.setPay_dt(people.getPay_dt());
                        peopleOldInfo.setChurch(people.getChurch());
                        peopleOldInfo.setMobile(people.getMobile());
                        peopleOldInfo.setMon(people.getMon());
                        peopleOldInfo.setTue(people.getTue());
                        peopleOldInfo.setWed(people.getWed());
                        peopleOldInfo.setThu(people.getThu());
                        peopleOldInfo.setFri(people.getFri());
                        peopleOldInfo.setSat(people.getSat());
                        peopleOldInfo.setLayman(people.getLayman());
                        peopleOldInfo.setNorthkorean(people.getNorthkorean());
                        peopleOldInfo.setPastor(people.getPastor());

                        if(peopleOldInfo.getComplete_pay()==0 || peopleOldInfo.getComplete_pay() == null){
                            peopleOldInfo.setComplete_pay(people.getComplete_pay());
                        }

                        peopleDao.save(peopleOldInfo);
                    }
                }
            }catch (Exception e ){
                result += peopleKey+"-"+name +" / "+e;
                logger.error("참석자 동기화 오류 - 참석자:"+peopleKey+"/Exception:"+e);
            }

        }

        return result;
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
    private PeopleEntity setPeopleData(JSONArray valueArr) {
        PeopleEntity people = new PeopleEntity();

        people.setPeoplekey((String)valueArr.get(PeopleEnum.PEOPLE_KEY.getIndexNum()));
        people.setName((String)valueArr.get(PeopleEnum.NAME.getIndexNum()));
        people.setMobile((String)valueArr.get(PeopleEnum.MOBILE.getIndexNum()));
        people.setGender((String)valueArr.get(PeopleEnum.GENDER.getIndexNum()));

        String agetmp = ((String) valueArr.get(PeopleEnum.AGE.getIndexNum())).trim();
        agetmp =  agetmp.isEmpty() ? "0" : agetmp;
        try{
            people.setAge(Integer.parseInt(agetmp.trim()));
        }catch (Exception e){

            logger.error("apiException:"+e);

        }


        people.setChurch((String)valueArr.get(PeopleEnum.CHURCH.getIndexNum()));

//        people.setNorth_korean((String)valueArr.get(PeopleEnum.NORTH_KOREAN.getIndexNum()));
//        people.setLayman((String)valueArr.get(PeopleEnum.LAYMAN.getIndexNum()));
//        String completePay = (String)valueArr.get(PeopleEnum.COMPLETE_PAY.getIndexNum());



        String firstpay = ((String)valueArr.get(PeopleEnum.FIRST_PAY.getIndexNum())).isEmpty()? "0" :(String)valueArr.get(PeopleEnum.FIRST_PAY.getIndexNum());
        firstpay = firstpay == null ? "0":firstpay;
        try{
            people.setFirst_pay(Integer.parseInt(firstpay));
        }catch(Exception e){
            people.setFirst_pay(0);
        }

        String secpay = ((String)valueArr.get(PeopleEnum.SEC_PAY.getIndexNum())).isEmpty() ? "0" : (String)valueArr.get(PeopleEnum.SEC_PAY.getIndexNum());
        secpay = secpay == null ? "0":secpay;
        try{
            people.setSec_pay(Integer.parseInt(secpay));
        }catch(Exception e){
            people.setSec_pay(0);
        }

        String completePay = ((String) valueArr.get(PeopleEnum.COMPLETE_PAY.getIndexNum())).isEmpty()  ? "0" : (String) valueArr.get(PeopleEnum.COMPLETE_PAY.getIndexNum());

        completePay = completePay == null ? "0":completePay;
        try{
            people.setComplete_pay(Integer.parseInt(completePay));
        }catch(Exception e){
            people.setComplete_pay(0);
        }

        people.setPay_dt((String)valueArr.get(PeopleEnum.PAY_DT.getIndexNum()));
        people.setPay_status((String)valueArr.get(PeopleEnum.PAY_STATUS.getIndexNum()));
        people.setReg_dt((String)valueArr.get(PeopleEnum.REG_DT.getIndexNum()));
        people.setPeople_key_sec((String)valueArr.get(PeopleEnum.PEOPLE_KEY_SEC.getIndexNum()));

        String mon = valueArr.getString(PeopleEnum.MON.getIndexNum());
        String tue = valueArr.getString(PeopleEnum.TUE.getIndexNum());
        String wed = valueArr.getString(PeopleEnum.WED.getIndexNum());
        String thu = valueArr.getString(PeopleEnum.THU.getIndexNum());
        String fri = valueArr.getString(PeopleEnum.FRI.getIndexNum());
        String sat = valueArr.getString(PeopleEnum.SAT.getIndexNum());

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

        if(valueArr.length() > PeopleEnum.CAR.getIndexNum()){
            people.setCar(valueArr.getString(PeopleEnum.CAR.getIndexNum()));
        }
        if(valueArr.length() > PeopleEnum.BUS.getIndexNum()){
            people.setBus(valueArr.getString(PeopleEnum.BUS.getIndexNum()));
        }
        if(valueArr.length() > PeopleEnum.BICYCLE.getIndexNum()){
            people.setBicycle(valueArr.getString(PeopleEnum.BICYCLE.getIndexNum()));
        }
        if(valueArr.length() > PeopleEnum.HOW.getIndexNum()) {
            people.setHow(valueArr.getString(PeopleEnum.HOW.getIndexNum()));
        }
        if(valueArr.length() > PeopleEnum.PASTOR.getIndexNum()) {
            people.setPastor(valueArr.getString(PeopleEnum.PASTOR.getIndexNum()));
        }
        if(valueArr.length() > PeopleEnum.NORTH_KOREAN.getIndexNum()) {
            people.setNorthkorean(valueArr.getString(PeopleEnum.NORTH_KOREAN.getIndexNum()));
        }
        if(valueArr.length() > PeopleEnum.LAYMAN.getIndexNum()) {
            people.setLayman(valueArr.getString(PeopleEnum.LAYMAN.getIndexNum()));
        }

        return people;
    }

    public void setPeople(PeopleEntity peopleEntity) {

        peopleDao.save(peopleEntity);
    }
}
