package com.kh.spring_mvc.service;

import com.kh.spring_mvc.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    public List<MemberDTO> getAllMembers() {
        List<MemberDTO> list = new ArrayList<>();
        list.add(new MemberDTO("user1", "pass1", "User One", 25));
        list.add(new MemberDTO("user2", "pass2", "User Two", 30));
        list.add(new MemberDTO("user3", "pass3", "User Three", 22));
        list.add(new MemberDTO("user4", "pass4", "User Four", 28));
        return list;
    }
}
