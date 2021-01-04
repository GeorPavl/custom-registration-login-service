package com.custom_login_example.service.member;

import com.custom_login_example.dto.MemberDTO;
import com.custom_login_example.dto.UserDTO;
import com.custom_login_example.entity.Member;
import com.custom_login_example.entity.User;
import com.custom_login_example.repository.MemberRepository;
import com.custom_login_example.repository.RoleRepository;
import com.custom_login_example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /****/

    @Override
    public List<MemberDTO> findAll() {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        for (Member tempMember : memberRepository.findAllByOrderByLastNameAsc()){
            memberDTOS.add(writeMemberDTO(tempMember));
        }
        return memberDTOS;
    }

    @Override
    public MemberDTO writeMemberDTO(Member source) {
        MemberDTO result = new MemberDTO();
        result.setId(source.getId());
        result.setFirstName(source.getFirstName());
        result.setLastName(source.getLastName());
        result.setEmail(source.getEmail());
        User user = source.getUser();
        user.setRoles(source.getUser().getRoles());
        result.setUser(userService.writeUserDTO(user));
        return result;
    }

    @Override
    public Member dtoToEntity(MemberDTO source) {
        Member result = new Member();
        if (source.getId() != null){
            result.setId(source.getId());
        }
        if (source.getFirstName() != null){
            result.setFirstName(source.getFirstName());
        }
        if (source.getLastName() != null){
            result.setLastName(source.getLastName());
        }
        if (source.getEmail() != null){
            result.setEmail(source.getEmail());
        }
        if (source.getUser() != null){
            result.setUser(userService.dtoToEntity(source.getUser()));
        }
        if (source.getRoles() != null){
            result.getUser().setRoles(source.getRoles());
        }
        return result;
    }

    @Override
    public MemberDTO findByUser(UserDTO user) {
        Member member = memberRepository.findByUser(userService.dtoToEntity(user));
        if (member == null){
            throw new RuntimeException("Did not find member with username: " + user.getUsername());
        }
        return writeMemberDTO(member);
    }

    @Override
    public MemberDTO createMember(MemberDTO source) {
        Member result = dtoToEntity(source);
        result.getUser().setEnabled(true);
        result.getUser().setPassword(passwordEncoder.encode(source.getUser().getPassword()));

        result.getUser().setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return writeMemberDTO(memberRepository.save(result));
    }
}
