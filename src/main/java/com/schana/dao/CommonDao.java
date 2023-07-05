package com.schana.dao;

import com.schana.dto.LoginDto;
import com.schana.entity.MemberEntity;
import com.schana.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
public class CommonDao {

    @Autowired
    private MemberRepository memberRepository;

    public MemberEntity getmember(MemberEntity memberEntity){
        return memberRepository.findByIdAndPassword(memberEntity.getId(), memberEntity.getPassword());
    }

    public MemberEntity saveMember(MemberEntity memberEntity){
        return memberRepository.save(memberEntity);
    }

    public List<MemberEntity> getmemberList() {
        return memberRepository.findAll();
    }
}
