package com.schana.dao;

import com.schana.dto.LoginDto;
import com.schana.entity.MemberEntity;
import com.schana.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonDao {

    @Autowired
    private MemberRepository memberRepository;

    public MemberEntity getmember(MemberEntity memberEntity){
        return memberRepository.findByIdAndPassword(memberEntity.getId(), memberEntity.getPassword());
    }

}
