package com.schana.dao;

import com.schana.entity.AssemblyInfoEntity;
import com.schana.entity.RoomEntity;
import com.schana.entity.RoomInfoEntity;
import com.schana.entity.RoomMasterEntity;
import com.schana.repository.AssemblyRepository;
import com.schana.repository.RoomInfoRepository;
import com.schana.repository.RoomMasterRepository;
import com.schana.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyDao {

    @Autowired
    private AssemblyRepository assemblyRepository;

    public AssemblyInfoEntity getAssemblyInfo(){
        return assemblyRepository.findFirstBy();
    }

}
