package com.custom_login_example.controller;

import com.custom_login_example.configuration.MyUserDetailsService;
import com.custom_login_example.dto.MemberDTO;
import com.custom_login_example.dto.UserDTO;
import com.custom_login_example.service.member.MemberService;
import com.custom_login_example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserService userService;

    // add request mapping for /showMyLoginPage
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "login-page";
    }

    // add request mapping for /access-denied
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/showAdminPage")
    public String showAdminPage(Model theModel){
        List<MemberDTO> theMembers = memberService.findAll();

        theModel.addAttribute("members", theMembers);
        return "admin-index";
    }

    @GetMapping("/showUserPage")
    public String showUserPage(Model model, @AuthenticationPrincipal UserDetails currentUser){
        UserDTO user = userService.findUserByUsername(currentUser.getUsername());
        MemberDTO member = memberService.findByUser(user);

        model.addAttribute("currentMember", member);
        model.addAttribute("currentUser", user);
        return "user-index";
    }
}
