package com.schana.service;


import com.schana.dao.AssemblyDao;
import com.schana.dao.PeopleDao;
import com.schana.entity.AssemblyInfoEntity;
import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyService {

    @Autowired
    private AssemblyDao assemblyDao;

    public AssemblyInfoEntity getAssemblyInfo() {
        //TODO : DTO 와 entity 간의 데이터 핸들러 추가

        return assemblyDao.getAssemblyInfo();
    }
}
