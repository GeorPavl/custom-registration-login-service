package com.custom_login_example.service.member;

import com.custom_login_example.dto.MemberDTO;
import com.custom_login_example.dto.UserDTO;
import com.custom_login_example.entity.Member;

import java.util.List;

public interface MemberService {

    List<MemberDTO> findAll();

    MemberDTO writeMemberDTO(Member member);

    Member dtoToEntity(MemberDTO memberDTO);

    MemberDTO findByUser(UserDTO user);

    MemberDTO createMember(MemberDTO source);
}
