package com.custom_login_example.controller;

import com.custom_login_example.dto.MemberDTO;
import com.custom_login_example.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model){
        model.addAttribute("member", new MemberDTO());
        return "registration-form";
    }

    @PostMapping("/createMember")
    public String createMember(@ModelAttribute MemberDTO memberDTO, Model model){
            model.addAttribute("newMember", memberService.createMember(memberDTO));
            return "login-page";
    }
}
