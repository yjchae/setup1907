package com.schana.service;


import com.schana.dao.ApiDao;
import com.schana.dao.AssemblyDao;
import com.schana.entity.AssemblyInfoEntity;
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

@Service
public class ApiService {

    @Autowired
    private ApiDao apiDao;

    /**
     * 구글 문서와 참석자 동기화
     */
    public void syncPeople() throws IOException {
        String stringURL = "https://sheets.googleapis.com/v4/spreadsheets/1hbbwUl0t--HRmzQT2E4x982vWhJECYXdShY76leGWIk/values/A1:Z10000001?key=AIzaSyB6WH0-v6d-EEdo4NK6-qlHG7Ml4jxgL5c";

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
        JSONObject jsonObj = new JSONObject(sb.toString());

        jsonObj.get("values");

        JSONArray jsonArray = jsonObj.getJSONArray("values");
        for(int i = 0 ; i < jsonArray.length() ; i++){
            JSONObject spaceJSON = jsonArray.getJSONObject(i);

        }

    }

}
