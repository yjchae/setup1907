package com.schana.service;


import com.schana.dao.CommonDao;
import com.schana.dto.LoginDto;
import com.schana.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    private CommonDao commonDao;

    public LoginDto getMember(LoginDto loginDto) {
        //권한체크
        MemberEntity memberEntity = MemberEntity.builder()
                .id(loginDto.getId())
                .password(loginDto.getPassword())
                .build();
        MemberEntity resultMember = commonDao.getmember(memberEntity);

        if(resultMember == null){
            return null;
        }else{
            LoginDto login = new LoginDto();
            login.setId(resultMember.getId());
            login.setPassword(resultMember.getPassword());
            login.setType(resultMember.getTypename());

            return login;
        }
    }
}
